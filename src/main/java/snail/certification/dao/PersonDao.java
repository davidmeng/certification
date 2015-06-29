package snail.certification.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import snail.certification.pojo.Person;
import snail.certification.pojo.User;

public class PersonDao extends BaseDaoImpl<Person> {

	protected PersonDao() {
		super(Person.class);
	}

	public Person getByNameAndId(String name) {
		@SuppressWarnings("unchecked")
		List<Person> list = sessionFactory.getCurrentSession().createCriteria(User.class).add(Restrictions.eq("name", name)).list();

		if ((list != null) && (list.size() > 0)) {
			return list.get(0);
		}

		return null;
	}

}
