package by.htp.ts.dao;

import java.util.List;

import by.htp.ts.bean.User;
import by.htp.ts.bean.UserPlusLogPass;
import by.htp.ts.bean.UserProgress;

public interface UserDAO {
	User authorization(String login, String password) throws DAOException;
	boolean registration(UserPlusLogPass userPLP) throws DAOException;
	List<UserProgress> receiveProgress(int userId) throws DAOException;
}
