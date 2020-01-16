package by.htp.ts.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.ts.bean.Test;
import by.htp.ts.bean.User;
import by.htp.ts.command.Command;
import by.htp.ts.service.ServiceException;
import by.htp.ts.service.ServiceFactory;

public class CreatedTestsCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		if(session==null) {
			response.sendRedirect("Controller?command=go_to_authorization_page&message=Please log in");
			return;
		}
		
		try {
			int userId = ((User)session.getAttribute(CommandImplParameter.USER)).getId();
			List<Test> createdTestsList = new ArrayList<Test>();
			
			ServiceFactory service = ServiceFactory.getInstance();
			createdTestsList = service.getTestS().receiveCreatedTests(userId);
			session.setAttribute(CommandImplParameter.CREATED_TESTS, createdTestsList);
			
			response.sendRedirect("Controller?command=go_to_created_tests_page");
						
		}catch(ServiceException e) {
			response.sendRedirect("Controller?command=go_to_error_page&error_message=Connection has failed");
		}
		
	}

}
