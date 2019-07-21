package lxy.de.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lxy.de.spring.pojo.User;
import lxy.de.spring.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("users")
	public String toUsers() {
		return "users";
	}
	
	@RequestMapping("list")
	@ResponseBody
	public Map<String,Object> queryUserAll(){
		return this.userService.queryUserAll();
	}
	
	/*@RequestMapping("/page/add")
	public String toAdd() {
		return "user-add";
	}*/
	
	/*@RequestMapping("page/{pageName}")
	public String toPage(@PathVariable("pageName")String pageName) {
		return pageName;
	}*/
	
	@RequestMapping("save")
	@ResponseBody
	public Map<String,Integer> saveUser(@Valid User user,BindingResult result){
		Map<String,Integer> map = new HashMap<>();
		
		if(result.hasErrors()) {
			List<FieldError> errors = result.getFieldErrors();
			for (FieldError error : errors) {
				System.out.println(error.getDefaultMessage());
				map.put("status", 500);
				return map;
			}
		}
		
		int b = this.userService.addUser(user);
		if(b==1) {
			map.put("status", 200);
		}else{
			map.put("status", 500);
		}
		System.out.println(200);
		return map;
		
	}
	
	@RequestMapping("edit")
	@ResponseBody
	public Map<String,Integer> editUser(User user){
		Map<String,Integer> map = new HashMap<>();
		boolean b = this.userService.editUser(user);
		if(b) {
			map.put("status", 200);
		}else {
			map.put("status", 500);
		}
		return map;
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public Map<String,Integer> deleteUserById(@RequestParam("ids")List<Long> ids){
		Map<String,Integer> map = new HashMap<>();
		int i = this.userService.delete(ids);
		if(i>0) {
			map.put("status", 200);
		}else {
			map.put("status", 500);
		}
		return map;
		
	}
}
