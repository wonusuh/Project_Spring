package ch09;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String action = request.getParameter("action");
		String view = "";

		if (action == null) {
			getServletContext().getRequestDispatcher("/studentControl?action=list").forward(request, response);
		} else {
			switch (action) {
			case "list":
				view = list(request, response);
				break;
			case "inset":
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
		return "";
	}
}
