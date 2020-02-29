package dss.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dss.dto.UserDetails;
import dss.service.locator.ServiceLocator;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userName = request.getParameter("userName");
		String userPass = request.getParameter("userPass");
		UserDetails userDetails = new UserDetails();
		userDetails.setUserName(userName);
		userDetails.setUserPass(userPass);
		
		try {
			
			//validate user
			ServiceLocator.getDssService().validateLoginUser(userDetails);
			
			//save usename in session
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("username", userName);
			
			//redirect to instructions page
			response.sendRedirect("instructions.html");
		} catch (Exception e) {
			// in case of error forward it back to login page with the error message
			request.setAttribute("error", e.getMessage());
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
			requestDispatcher.forward(request, response);
		}
		
		
		
	}

}
