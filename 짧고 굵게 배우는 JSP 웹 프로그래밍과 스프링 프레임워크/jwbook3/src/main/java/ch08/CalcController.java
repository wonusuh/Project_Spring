package ch08;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calcControl")
public class CalcController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CalcController() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operator = request.getParameter("op");
		int n1 = Integer.parseInt(request.getParameter("n1"));
		int n2 = Integer.parseInt(request.getParameter("n2"));
		long result = 0;

		switch (operator) {
		case "+":
			result = (long) n1 + n2;
			break;
		case "-":
			result = n1 - (long) n2;
			break;
		case "*":
			result = n1 * (long) n2;
			break;
		case "/":
			result = n1 / n2;
			break;
		default:
			result = 1234567890;
		}

		System.out.println(result);
		request.setAttribute("result", result);
		getServletContext().getRequestDispatcher("/ch08/calcResult.jsp").forward(request, response);
//		response.sendRedirect(request.getContextPath() + "/ch08/calcResult.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
