package by.htp.ts.command.impl;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.ts.bean.User;
import by.htp.ts.command.Command;

public class GoToMainPage implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session==null) {
			response.sendRedirect("Controller?command=go_to_authorization_page&message=Session invalidate!!! Please sign in.");
			return;
		}
		User user = (User)session.getAttribute(CommandImplParameter.USER);
		
		Enumeration<String> attribute = session.getAttributeNames();
		while (attribute.hasMoreElements()) {
			session.removeAttribute(attribute.nextElement());
		}
		
		session.setAttribute(CommandImplParameter.USER,user);
		session.setAttribute(CommandImplParameter.GOTO_REQUEST,"/WEB-INF/jsp/main.jsp");
		request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
		
	}
	

}
