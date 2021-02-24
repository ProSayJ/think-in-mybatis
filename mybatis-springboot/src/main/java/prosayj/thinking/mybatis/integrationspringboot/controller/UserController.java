package prosayj.thinking.mybatis.integrationspringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import prosayj.thinking.mybatis.integrationspringboot.service.UserService;
import prosayj.thinking.mybatis.simple.model.SysUser;

import java.util.List;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("users/{id}")
	SysUser user(@PathVariable("id") Long id){
		return userService.findById(id);
	}
	
	@RequestMapping("users")
	List<SysUser> users(){
		return userService.findAll();
	}
}
