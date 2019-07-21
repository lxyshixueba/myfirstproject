package lxy.de.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lxy.de.spring.pojo.User;
import lxy.de.spring.service.UserService;


/**
 * @author lxy
 * 2019年6月19日 下午5:35:27  
 *	@ResponseBody可以直接返回Json结果，

@ResponseEntity不仅可以返回json结果，还可以定义返回的HttpHeaders和HttpStatus
 */
@Controller
@RequestMapping("rest/user")
public class RestUserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("{id}")
	public ResponseEntity<User> queryUserById(@PathVariable("id")Long id){
		//参数不合法，响应400
		if(id==null||id.longValue()<=0) {
			//System.out.println(id);
			return ResponseEntity.badRequest().build();
		}
		User user = this.userService.queryUserById(id);
		if(user == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(user);
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping
	public ResponseEntity<Map<String, Object>> queryUserAll(){
		Map<String,Object> map = this.userService.queryUserAll();
		if(map.get("rows") == null||CollectionUtils.isEmpty((List<User>)map.get("rows"))){
			return ResponseEntity.notFound().build();
		}
		//找到了响应200
		return ResponseEntity.ok(map);
	}
	
	@PostMapping
	public ResponseEntity<Void> saveUser(User user){
		 if(user == null || StringUtils.isEmpty(user.getName())) {
			 return ResponseEntity.badRequest().build();
		 }
		 int i = this.userService.addUser(user);
		 if(i!=1) {
			 return ResponseEntity.badRequest().build();
		 }
		 return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping
	public ResponseEntity<Void> editUser(User user){
		if(user==null||StringUtils.isEmpty(user.getName())) {
			return ResponseEntity.badRequest().build();
		}
		boolean editUser = this.userService.editUser(user);
		if(!editUser) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@DeleteMapping
	public ResponseEntity<Void> deleteUserById(@RequestParam("id")Long id){
		if(id == null || id.longValue()<=0) {
			return ResponseEntity.badRequest().build();
		}
		int i = this.userService.delete(id);
		if(i!=1) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@DeleteMapping("money")
	public ResponseEntity<Void> deleteUser(@RequestParam("ids")List<Long> ids){
		if(CollectionUtils.isEmpty(ids)) {
			return ResponseEntity.badRequest().build();
		}
		int i = this.userService.delete(ids);
		if(i<=0) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}


	