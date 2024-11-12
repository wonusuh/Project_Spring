package ch10;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;

@WebServlet(description = "This is a controller for practice.", urlPatterns = { "/news.nhn" })
@MultipartConfig(maxFileSize = 1024 * 1024 * 2, location = "C:\\wonuSuhGram\\temporaryImages")
public class NewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NewsDAO dao;
	private ServletContext ctx;
	// 웹 리소스 기본경로 지정
	private final String START_PAGE = "ch10/newsList.jsp";

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao = new NewsDAO();
		ctx = getServletContext();
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 한글 깨짐현상을 방지하기위한 설정입니다.
		String action = request.getParameter("action");

		dao = new NewsDAO();

		// 자바 리플레션을 사용해 if(switch) 없이 요청에 따라 구현 메서드가 실행되도록 구성합니다.
		Method m;
		String view = null;

		// 파라미터가 없이 접근한 경우 메인화면(뉴스목록)으로 보냅니다.
		if (action == null) {
			action = "listNews";
		}

		try {
			// 현재 클래스에서 action 이름과 HttpServletRequest를 파라미터로 하는 메서드를 찾아서 Method 객체에 저장합니다.
			m = this.getClass().getMethod(action, HttpServletRequest.class);

			// 메서드를 실행한 후 리턴값을 받아옵니다.
			view = (String) m.invoke(this, request);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			ctx.log("요청 action 이 없습니다!!");
			request.setAttribute("error", "action 파라미터가 잘못되었습니다!!");
			view = START_PAGE;
		} catch (Exception e) {
			e.printStackTrace();
		}

		// POST 요청을 포워딩 하는 경우 새로고침을 하면 요청이 한 번더 이루어지는 문제 때문에 리디렉션과 포워딩 으로 나누어 구현해야합니다.
		if (view.startsWith("redirect:/")) {
			String rview = view.substring("redirect:/".length());
			response.sendRedirect(rview);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		}
	}

	// 뉴스를 추가하는 메서드입니다.
	public String addNews(HttpServletRequest req) {
		NewsVO n = new NewsVO();
		try {
			// 이미지 파일 저장
			Part part = req.getPart("file");
			String fileName = getFilename(part);
			if (fileName != null && !fileName.isEmpty()) {
				part.write(fileName);
			}

			// 입력값을 NewsVO 객체로 매핑
			BeanUtils.populate(n, req.getParameterMap());

			// 이미지 파일 이름을 NewsVO 객체에도 저장
			n.setImg("/img" + fileName);

			dao.addNews(n);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("뉴스 등록 과정에서 문제가 발생했습니다!!");
			req.setAttribute("error", "뉴스가 정상적으로 등록되지 않았습니다!!");
			return listNews(req);
		}
		return "redirect:/news.nhn?action=listNews";
	}

	// Part 객체로 전달된 이미지 파일로부터 파일 이름을 추출하기 위한 메서드입니다.
	private String getFilename(Part part) {
		String fileName = null;
		// 파일 이름이 들어있는 헤더 영역을 가져옴
		String header = part.getHeader("content-disposition");
		System.out.println("Header -> " + header);

		// 파일 이름이 들어있는 속성부분의 시작 위치를 가져와 " " 사이의 값만 가지고 옴
		int start = header.indexOf("filename=");
		fileName = header.substring(start + 10, header.length() - 1);
		ctx.log("파일명 : " + fileName);
		return fileName;
	}

	// 뉴스 목록을 보여주기위한 요청을 처리하는 메서드입니다.
	public String listNews(HttpServletRequest req) {
		List<NewsVO> list;
		try {
			list = dao.getAll();
			req.setAttribute("newsList", list);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("뉴스 목록 생성 과정에서 문제가 발생했습니다!!");
			req.setAttribute("error", "뉴스 목록이 정상적으로 처리되지 않았습니다.");
		}
		return "ch10/newsList.jsp";
	}

	// 특정 뉴스기사를 클릭했을 때 호출하기위한 요청을 처리하는 메서드입니다.
	public String getNews(HttpServletRequest req) {
		int aid = Integer.parseInt(req.getParameter("aid"));
		try {
			NewsVO n = dao.getNews(aid);
			req.setAttribute("news", n);
		} catch (SQLException e) {
			e.printStackTrace();
			ctx.log("뉴스를 가져오는 과정에서 문제가 발생했습니다!!");
			req.setAttribute("error", "뉴스의 상세 정보를 정상적으로 가져오지 못했습니다!!");
		}
		return "ch10/newsView.jsp";
	}

	// 뉴스를 삭제하기위한 메서드입니다. 삭제의 경우에도 리디렉션으로 이동할 수 있도록 합니다.
	public String deleteNews(HttpServletRequest req) {
		int aid = Integer.parseInt(req.getParameter("aid"));
		try {
			dao.delNews(aid);
		} catch (SQLException e) {
			e.printStackTrace();
			ctx.log("뉴스 삭제 과정에서 문제가 발생했습니다!!");
			req.setAttribute("error", "뉴스가 정상적으로 삭제되지 않았습니다!!");
		}
		return "redirect:/news.nhn?action=listNews";
	}

	/** 리액트 교재를 마치고 이어서 공부합니다. test*/
}
