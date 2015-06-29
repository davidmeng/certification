package snail.certification.biz;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import snail.certification.pojo.User;

public class UserDetailsServiceImpl implements UserDetailsService {
	IUserServiceBiz userServiceBiz;

	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException, DataAccessException {
		User user = userServiceBiz.getUserByUserName(userName);
		if (user == null) {
			return null;
		}

		MyUserDetails userDetails = new MyUserDetails(user);
		return userDetails;
	}
}
