package filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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

//Bu sÄ±nÄ±fta yazdÄ±klarÄ±mdan emin deÄŸilim. Ä°lerde sayfa sayÄ±sÄ± arttÄ±kÃ§a burayÄ±
//artÄ±rmak gerekir. Dinamik hale getirmek gerek. Åžimdilik Ã§Ã¶zÃ¼m bulamadÄ±m.
@WebFilter("*")
public class SessionFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

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
		//Kullanıcı giriş yapmamışsa giriş sayfasına yönlendiriyoruz.
		//Burada js,css gibi kaynaklarımız'da engelleneceği için içerisinde resources geçen
		//linklere karışma diyoruz. Yada .jpg'lere de karışma diyoruz.
		//Birde son olarak sigIn ve signUp sayfaları olmayıp onlar mainForm içerisinde
		//Onları sadece kontrol için kullandığımız için onlarada izin ver diyoruz.
		if ((user == null) && !(uri.contains("signInController") ||uri.contains("resources") ||
				uri.contains(".jpg") || uri.contains("signUpController") || uri.contains("mainController"))) {
			resp.sendRedirect(req.getContextPath() + "/mainController");
		} 
		// Kullanıcı giriş yapmışşa MainForm sayfasına gidemez. Giriş ve Kayıt Ol ile işi olmayacaktır.
		else if (user!=null && uri.contains("mainController")) {
			System.out.println("geldi3");
			resp.sendRedirect(req.getContextPath() + "/taskController");
		}
		else {
			System.out.println("geldi4");
			chain.doFilter(request, response);
		}
		

	}
}
