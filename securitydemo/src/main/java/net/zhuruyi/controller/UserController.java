package net.zhuruyi.controller;


import com.fasterxml.jackson.annotation.JsonView;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import net.zhuruyi.DTO.User;
import net.zhuruyi.DTO.UserQueryCondition;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author :zhuruyi
 * @Description:
 * @Date:Create in 22:19 2018/3/7
 * @Modified By:
 */
@RequestMapping("/user")
@RestController
public class UserController {

    @PostMapping
    public User create(@Valid @RequestBody User user, BindingResult errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().stream()
                    .forEach(error -> System.out.println(error.getDefaultMessage()));
        }

        System.out.println(user.toString());
        user.setId(1L);
        return user;
    }

    @JsonView(User.userSimpleView.class)
    @GetMapping
    public List<User> query(
            UserQueryCondition condition,
            @PageableDefault(page = 2, size = 17, sort = "username,asc") Pageable pageable) {

        System.out.println(
                ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));

        System.out.println(pageable.getPageSize());
        System.out.println(pageable.getPageNumber());
        System.out.println(pageable.getSort());

        List<User> users = new ArrayList<User>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }


    @JsonView(User.userDetailView.class)
    @GetMapping(value = "/{id:\\d+}")
    public User getInfo(@PathVariable("id") String id) {
        User user = new User();
        user.setUserName("tom");
        return user;
    }

}



















