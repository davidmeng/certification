package snail.certification.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import snail.certification.pojo.User;

@Repository
public class UserDao extends BaseDaoImpl<User> {

	protected UserDao() {
		super(User.class);
	}

	public User getUserByUserName(String userName) {
		@SuppressWarnings("unchecked")
		List<User> list = sessionFactory.getCurrentSession().createCriteria(User.class).add(Restrictions.eq("userName", userName)).list();

		if ((list != null) && (list.size() > 0)) {
			return list.get(0);
		}

		return null;
	}

}
