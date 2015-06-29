package snail.certification.biz;

import snail.certification.pojo.User;

public interface IUserBiz {
	
	public User getUserById(Integer paramInteger);
	public void saveUser(User paramUser);
	public User getUserByUserName(String paramString);
	
}