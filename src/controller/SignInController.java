package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.UserDAOImpl;
import model.User;

@WebServlet(urlPatterns = "/signInController", name = "userLogin")
public class SignInController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Bu sayfaya direk url yazarak gelmek isteyenlere karşı 404 sayfasına yönlendiriyoruz.
		request.getRequestDispatcher("view/404Page.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
		
		String eMail = req.getParameter("inputEmailSignIn");
		String password = req.getParameter("inputPasswordSignIn");
		
		UserDAOImpl dao = new UserDAOImpl();
		User user = dao.isPasswordValid(eMail, password);
		//Kullanıcı giriş yapmamışsa işlem sayfasına gitmesine izin vermiyoruz.
		if (user == null) {
			req.getRequestDispatcher("view/MainForm.jsp").forward(request, response);
		} else {
			HttpSession session = req.getSession();
			session.setAttribute("userInformation", user);
			resp.sendRedirect("taskController");
		}
	}

}
