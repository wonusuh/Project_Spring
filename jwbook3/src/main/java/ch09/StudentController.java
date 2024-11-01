package ch09;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

@WebServlet(description = "This is a controller for practice.", urlPatterns = { "/studentControl" })
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	StudentDAO dao;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao = new StudentDAO();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		String view = "";

		if (action == null) {
			getServletContext().getRequestDispatcher("/studentControl?action=list").forward(request, response);
		} else {
			switch (action) {
			case "list":
				view = list(request, response);
				break;
			case "insert":
				view = insert(request, response);
				break;
			}

			getServletContext().getRequestDispatcher("/ch09/" + view).forward(request, response);
		}
	}

	private String list(HttpServletRequest req, HttpServletResponse res) {
		req.setAttribute("allStudents", dao.getAllStudents());
		return "studentInfo.jsp";
	}

	private String insert(HttpServletRequest req, HttpServletResponse res) {
		Student s = new Student();

		try {
			// 요청의 문자 인코딩 설정
//			req.setCharacterEncoding("UTF-8"); // 요청 인코딩 설정
			BeanUtils.populate(s, req.getParameterMap());

			System.out.println("이름 : " + s.getUsername());
			System.out.println("대학 : " + s.getUniv());
			System.out.println("생일 : " + s.getBirth());
			System.out.println("이메일 : " + s.getEmail());
			System.out.println("전화 : " + s.getTel());
		} catch (Exception e) {
			e.printStackTrace();
		}
		dao.insert(s);
		return list(req, res);
	}
}
