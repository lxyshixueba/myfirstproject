package lxy.de.spring.pojo;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User implements Serializable {
	/**
	 * 　那么我们在什么情况下需要使用到Serializable接口呢？？
		　　1、当你想把的内存中的对象状态保存到一个文件中或者数据库中时候；
		　　2、当你想用套接字在网络上传送对象的时候；
		　　3、当你想通过RMI传输对象的时候；
	 */
    private static final long serialVersionUID = 844067285207924072L;

    private Long id;

    // 用户名
    @Length(min=6,max=20,message="用户名必须在6-20之间")
    private String userName;

    // 密码
    @JsonIgnore
    @Length(min=6, max=20, message="密码必须在6-20位之间")
    private String password;

    // 姓名
    private String name;

    // 年龄
    @Range(min=1,max=100,message="年龄必须在1-100之间")
    private Integer age;

    // 性别，1男性，2女性
    private Integer sex;

    // 出生日期
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birthday;

    // 创建时间
    private Date created;

    // 更新时间
    private Date updated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", name=" + name
                + ", age=" + age + ", sex=" + sex + ", birthday=" + birthday + ", created=" + created
                + ", updated=" + updated + "]";
    }

}
