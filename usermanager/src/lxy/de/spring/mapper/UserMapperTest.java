package lxy.de.spring.mapper;

import static org.junit.Assert.fail;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lxy.de.spring.pojo.User;

public class UserMapperTest {
	
	private UserMapper userMapper;
	
	@Before
	public void setUp() throws Exception {
		/*String resource = "";
		InputStream stream = Resources.getResourceAsStream(resource);*/
		
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml", "spring/applicationContext-mybatis.xml");
		this.userMapper = (UserMapper)context.getBean("userMapper");
	}

	@Test
	public void testQueryUserById() {
		 System.out.println(this.userMapper.queryUserById(1l));
	}
	
	@Test
	public void testAddUser() {
		User user = new User();
		user.setName("第三");
		user.setUserName("disna");
		user.setAge(20);
		user.setPassword("4562123");
		this.userMapper.addUser(user);
	}

}
