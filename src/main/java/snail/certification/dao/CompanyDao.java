package snail.certification.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import snail.certification.pojo.Company;

public class CompanyDao extends BaseDaoImpl<Company> {

	protected CompanyDao() {
		super(Company.class);
	}

	
	public Company getByNameAndId(String name) throws Exception {
		
		@SuppressWarnings("unchecked")
		List<Company> list = sessionFactory.getCurrentSession().createCriteria(Company.class).add(Restrictions.eq("name", name)).list();

		if ((list == null) || (list.size() == 0)) {
			return null;
		}
		if (list.size() > 1) {
			throw new Exception("two same companies");
		}
		if (list.size() == 1) {
			return list.get(0);
		}
		return null;
  }
}
