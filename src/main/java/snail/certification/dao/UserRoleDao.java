package snail.certification.dao;

import org.springframework.stereotype.Repository;

import snail.certification.pojo.UserRole;

@Repository
public class UserRoleDao extends BaseDaoImpl<UserRole> {

	protected UserRoleDao() {
		super(UserRole.class);
	}

}
