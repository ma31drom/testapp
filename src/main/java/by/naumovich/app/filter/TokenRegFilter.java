package by.naumovich.app.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter
public class TokenRegFilter implements Filter {

	public static final String TOKEN = "x-token";

	static public ThreadLocal<String> tokenLocal;

	public TokenRegFilter() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.getAttribute(TOKEN);
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

}
