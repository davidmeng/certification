package snail.certification.biz;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import snail.certification.pojo.Role;
import snail.certification.pojo.User;
import snail.certification.pojo.UserRole;

public class MyUserDetails implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	User user;
	private Set<SimpleGrantedAuthority> authorityArray = new HashSet<>();

	MyUserDetails(User user) {
		this.user = user;
		initAuthorities();
	}

	MyUserDetails() {
	}

	public void initAuthorities() {
		List<Role> roleList = new ArrayList<>();
		for (Iterator<UserRole> itor = user.getUserRoles().iterator(); itor
				.hasNext();) {
			UserRole userRole = (UserRole) itor.next();
			roleList.add(userRole.getRole());
		}

		for (int i = 0; i < roleList.size(); i++) {
			authorityArray.add(new SimpleGrantedAuthority(roleList.get(i).getRoleName()));
		}
	}

	public String getPassword() {
		return user.getPassword();
	}

	public String getUsername() {
		return user.getUserName();
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return true;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorityArray;
	}
}
