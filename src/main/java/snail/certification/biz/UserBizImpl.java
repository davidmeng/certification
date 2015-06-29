package snail.certification.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import snail.certification.dao.UserDao;
import snail.certification.dao.UserRoleDao;
import snail.certification.pojo.Role;
import snail.certification.pojo.User;
import snail.certification.pojo.UserRole;

@Component("userBiz")
public class UserBizImpl implements IUserBiz {
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	UserRoleDao userRoleDao;

	public User getUserById(Integer id) {
		return userDao.getById(id);
	}

	@Transactional(readOnly = true)
	public User getUserByUserName(String userName) {
		User user = userDao.getUserByUserName(userName);

		return user;
	}

	public void saveUser(User user) {
		Role r = new Role();
		UserRole ur = new UserRole();
		r.setId(Integer.valueOf(Integer.parseInt("3")));
		ur.setRole(r);

		userDao.save(user);

		ur.setUser(user);
		userRoleDao.save(ur);
	}
}