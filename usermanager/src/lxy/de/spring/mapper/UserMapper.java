package lxy.de.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import lxy.de.spring.pojo.User;

public interface UserMapper {
	/**
	 * 根据用户id查询用户
	 * @param id
	 * @return
	 */
	public User queryUserById(Long id);
	/**
	 * 查询总条数
	 * @return
	 */
	public Long queryCount();
	
	/**
	 * 查询所有用户
	 * @return
	 */
	public List<User> queryUserAll();
	
	public int addUser(User user);

	public int editUser(User user);

	public int deleteUserById(@Param("ids")List<Long> ids);

	public int deleteUserByIda(Long id);
}
