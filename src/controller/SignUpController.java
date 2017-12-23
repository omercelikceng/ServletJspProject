package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.IUserDAO;
import dao.UserDAOImpl;
import model.User;
import nl.captcha.Captcha;

@WebServlet(urlPatterns="/signUpController",name="userSignUp")
public class SignUpController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Bu sayfaya direk url yazarak gelmek isteyenlere karşı 404 sayfasına yönlendiriyoruz.
		req.getRequestDispatcher("WEB-INF/view/404Page.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("inputName");
		String surname = request.getParameter("inputSurname");
		String eMail = request.getParameter("inputEmail");
		String password = request.getParameter("inputPassword");
		String code = request.getParameter("formCaptchaCode");
		
		HttpSession session = request.getSession();
		Captcha captcha = (Captcha)session.getAttribute(Captcha.NAME);
		
		boolean isCorrect = captcha.isCorrect(code);
		if(isCorrect) {
		User user = new User(name,surname,eMail,password);
		IUserDAO userDao = new UserDAOImpl();
		userDao.insertUser(user);
		session.setAttribute("userInformation", user);
		response.sendRedirect("taskController");
		}else {
			response.sendRedirect("mainController");
		}
	}
}
