package crm.servlets;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm.utils.TokenJWT;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(urlPatterns = { "/client", "/commande", "/historique" })
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		//chain.doFilter(request, response);
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String token = null, login = null;

		
		
//		Enumeration headerNames = req.getHeaderNames();
//		while(headerNames.hasMoreElements()) {
//		  String headerName = (String)headerNames.nextElement();
//		  System.out.print("" + headerName);
//		  System.out.println(": " + req.getHeader(headerName));
//		}
		
		
		if(req.getHeader("Authorization")!= null) {
			token = req.getHeader("Authorization").replaceAll("Bearer ", "");
			login = TokenJWT.verifyJWT(token);
//			if(token.equals("POEI")) {
//				login = "Ok";
//			}
		}
		if(login!=null) {
			request.setAttribute("loginValide", login);
			chain.doFilter(request, response);
		}
		else {
			resp.setStatus(403);
			resp.setContentType("text/plain");
			resp.getWriter().write("Connexion requise");
		}
		// pass the request along the filter chain
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
