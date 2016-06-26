package com.seframework.Foundation;

import java.io.File;
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

/**
 * Servlet Filter implementation class BaseFilter
 */
@WebFilter("/BaseFilter")
public class BaseFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public BaseFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest rq = (HttpServletRequest) request;
		HttpServletResponse rp = (HttpServletResponse) response;

		String realPath = rq.getServletContext().getRealPath("/");
		String uri = rq.getRequestURI();

		File file = new File(realPath + uri);
		if (!file.exists() && !file.isDirectory()) {
			String rewriteUrl = "/index";
			rewriteUrl += "?" + uri.trim();

			String query = rq.getQueryString();
			if (query != null) {
				query = query.toString();
				rewriteUrl += "?" + query;
			}

			if (null != rewriteUrl) {
				request.getRequestDispatcher(rewriteUrl).forward(rq, rp);
				return;
			}
		}

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
