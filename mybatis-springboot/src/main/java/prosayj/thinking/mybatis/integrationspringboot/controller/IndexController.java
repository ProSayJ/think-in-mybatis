package prosayj.thinking.mybatis.integrationspringboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
	
	@RequestMapping("/")
	String home(){
		return "Hello World!";
	}
}
