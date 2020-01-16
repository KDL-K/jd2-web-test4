package by.htp.ts.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.ts.bean.User;
import by.htp.ts.bean.UserProgress;
import by.htp.ts.command.Command;
import by.htp.ts.service.ServiceException;
import by.htp.ts.service.ServiceFactory;

public class ProgressCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String USER_PROGRESS = "user_progress";
		HttpSession session=request.getSession(false);
		if(session==null) {
			response.sendRedirect("Controller?command=go_to_authorization_page&message=Please log in");
			return;
		}
		try {
			int userId = ((User)session.getAttribute(CommandImplParameter.USER)).getId();
			List<UserProgress> userProgressList = new ArrayList<UserProgress>();
			ServiceFactory service = ServiceFactory.getInstance();
			userProgressList = service.getClientS().receiveProgress(userId);
			session.setAttribute(USER_PROGRESS, userProgressList);
	
			session.setAttribute(CommandImplParameter.GOTO_REQUEST, "/WEB-INF/jsp/userprogress.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/userprogress.jsp").forward(request, response);
		}catch(ServiceException e) {
			response.sendRedirect("Controller?command=go_to_error_page&error_message=Connection has failed");
		}
		
	}

}
