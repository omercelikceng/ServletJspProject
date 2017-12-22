package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(urlPatterns= {"/signUpController","/signInController","/taskController"})
public class XSSFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		if(req.getMethod().equalsIgnoreCase("POST")) {
			chain.doFilter(new XSSRequestWrapper(req), response);
		}
		else {
			chain.doFilter(request, response);
		}
	}

}
