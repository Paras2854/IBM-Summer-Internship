package dss.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dss.dto.UserDetails;
import dss.service.locator.ServiceLocator;

/**
 * Servlet implementation class SignUpServlet
 */
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. getting values from html form
		String userName= request.getParameter("userName");
		String userPass= request.getParameter("userPass");
		UserDetails userDetails = new UserDetails();
		try {
			//2. setting values in User Details dto object
			userDetails.setUserName(userName);
			userDetails.setUserPass(userPass);
			
			ServiceLocator.getDssService().validateDuplicate(userDetails);
			
			//3. calling service method for saving the user object to database
			ServiceLocator.getDssService().addUser(userDetails);
			
			request.setAttribute("success", "Account created successfully! Please login to continue");
			
		} catch (Exception e) {
			
			request.setAttribute("error",e.getMessage());
			
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
		requestDispatcher.forward(request, response);
		
	}

}
