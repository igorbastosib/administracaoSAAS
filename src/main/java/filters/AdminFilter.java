package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.ServletUtil;
import static servlet.ServletUtil.forward;


@WebFilter(urlPatterns = { "/saas-admin/*"})
public class AdminFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String uri = req.getRequestURI();
		HttpServletResponse resp = (HttpServletResponse) response;

		if (ServletUtil.validaLogin(req, resp)) {
			chain.doFilter(req, resp);
		} else {
			forward(req, resp, "/login-admin.saas");
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}

}
