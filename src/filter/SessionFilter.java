package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;

//Bu sınıfta yazdıklarımdan emin değilim. İlerde sayfa sayısı arttıkça burayı
//artırmak gerekir. Dinamik hale getirmek gerek. Şimdilik çözüm bulamadım.
@WebFilter("*")
public class SessionFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		System.out.println("Session Filter");

		HttpServletResponse resp = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		HttpSession session = req.getSession();
		User user = null;
		if (session.getAttribute("userInformation") != null) {
			user = (User) session.getAttribute("userInformation");
		}

		String uri = req.getRequestURI();

		if ((user == null) && !(uri.contains("MainForm.jsp") || uri.contains("signInController")
				|| uri.contains("resources") || uri.contains(".jpg") || uri.contains("signUpController"))) {
			resp.sendRedirect(req.getContextPath() + "/view/MainForm.jsp");
		} else if (user != null && (uri.endsWith("MainForm.jsp") || uri.endsWith("UserOperation.jsp"))) {
			resp.sendRedirect(req.getContextPath() + "/taskController");
		} else {
			chain.doFilter(request, response);
		}

	}
}
