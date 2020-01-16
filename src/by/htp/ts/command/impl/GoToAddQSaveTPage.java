package by.htp.ts.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.ts.command.Command;

public class GoToAddQSaveTPage implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session==null) {
			response.sendRedirect("Controller?command=go_to_authorization_page&message=Session invalidate!!! Please sign in.");
			return;
		}
		
		session.setAttribute(CommandImplParameter.GOTO_REQUEST,"/WEB-INF/jsp/addqsavet.jsp");
		request.getRequestDispatcher("/WEB-INF/jsp/addqsavet.jsp").forward(request, response);
		
	}

}
