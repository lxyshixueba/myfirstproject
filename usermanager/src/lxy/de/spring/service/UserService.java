package lxy.de.spring.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lxy.de.spring.mapper.UserMapper;
import lxy.de.spring.pojo.User;

@Service
public class UserService {
    
    @Autowired
    private UserMapper userMapper;

    public Map<String, Object> queryUserAll() {
        Map<String, Object> map = new HashMap<>();
        
        // 查询用户的总条数
        Long count = this.userMapper.queryCount();
        map.put("total", count);
        
        // 查询所有用户信息
        List<User> users = this.userMapper.queryUserAll();
        map.put("rows", users);
        return map;
    }
    
    /**
     * 添加用户信息
     * @param user
     * @return
     */
    public int addUser(User user) {
    	return this.userMapper.addUser(user);
    }

	public boolean editUser(User user) {
		return this.userMapper.editUser(user) == 1;
	}

	public int delete(List<Long> ids) {
		return this.userMapper.deleteUserById(ids);
	}

	public User queryUserById(Long id) {
		return this.userMapper.queryUserById(id);
	}

	public int delete(Long id) {
		return this.userMapper.deleteUserByIda(id);
	}

}
