package by.htp.ts.service;

import java.util.List;

import by.htp.ts.bean.User;
import by.htp.ts.bean.UserPlusLogPass;
import by.htp.ts.bean.UserProgress;

public interface ClientService {
	User signIn(String login, String password) throws ServiceException;
	boolean register(UserPlusLogPass userPLP)throws ServiceException;
	List<UserProgress> receiveProgress(int userId) throws ServiceException;
}
