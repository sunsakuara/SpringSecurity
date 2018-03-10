package net.zhuruyi.DTO;

import com.fasterxml.jackson.annotation.JsonView;
import java.util.Date;
import javax.validation.constraints.Past;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Author :zhuruyi
 * @Description:
 * @Date:Create in 22:21 2018/3/7
 * @Modified By:
 */
public class User {

    private Long id;
    private String userName;
    @NotBlank(message = "密码不能为空")
    private String password;
    @Past
    private Date birthday;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // 在值对象的Get方法上 声明接口
    @JsonView(userSimpleView.class)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @JsonView(userDetailView.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonView(userSimpleView.class)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                '}';
    }

    // 使用JsonView 1. 声明接口
    public interface userSimpleView {

    }

    public interface userDetailView extends userSimpleView {

    }
}
