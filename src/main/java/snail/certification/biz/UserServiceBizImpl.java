package snail.certification.biz;

import org.springframework.beans.factory.annotation.Autowired;

import snail.certification.dao.UserDao;
import snail.certification.pojo.User;

public class UserServiceBizImpl implements IUserServiceBiz {
	
	@Autowired
	UserDao userDao;

	public User getUserByUserName(String userName) {
		User user = userDao.getUserByUserName(userName);

		return user;
	}
}