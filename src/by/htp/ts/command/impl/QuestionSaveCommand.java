package by.htp.ts.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.ts.bean.Question;
import by.htp.ts.command.Command;
import by.htp.ts.service.ServiceException;
import by.htp.ts.service.ServiceFactory;
import by.htp.ts.service.TestService;

public class QuestionSaveCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String RIGHT_ANSWER = "right_answer";
		
		HttpSession session=request.getSession(false);
		if(session==null) {
			response.sendRedirect("Controller?command=go_to_authorization_page&message=Please log in");
			return;
		}
		
		Question question=(Question)session.getAttribute(CommandImplParameter.QUESTION);
		
		String[] rightAnswersId=request.getParameterValues(RIGHT_ANSWER);
		if(rightAnswersId == null) {
			request.getRequestDispatcher("/WEB-INF/jsp/rightanswersmark.jsp").forward(request, response);
			return;
		}

		try {
			int size = question.getAnswerList().size();
			for(String answerId : rightAnswersId) {
				int j = Integer.parseInt(answerId);
				for (int i = 0; i<size; i++) {
					if (j == question.getAnswerList().get(i).getId()) {
						question.getAnswerList().get(i).setIsRightAnswer(true);
					}
				}
			}
			
			ServiceFactory service=ServiceFactory.getInstance();
			TestService tService=service.getTestS();
			tService.addQuestion(question);
		
			response.sendRedirect("Controller?command=go_to_add_q_save_t_page&message=Question saved succesfully.");
			
		}catch(ServiceException e) {
			response.sendRedirect("Controller?command=go_to_error_page&error_message=Connection has failed");
		}

	}

}
