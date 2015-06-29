package snail.certification.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import snail.certification.biz.UserBizImpl;
import snail.certification.pojo.User;

@Controller
public class UserAction {

	@Autowired
	UserBizImpl userBiz;
	
	@RequestMapping("/addUser")
	public String addUser(){
		return "user/newUser" ;
	}
	
	@RequestMapping("/checkUserName")
	public @ResponseBody boolean checkUserName(String userName) {
		
		User user = userBiz.getUserByUserName(userName);
		if (user == null){
			return true ;
		}else {
			return false ;
		}
	}
}
