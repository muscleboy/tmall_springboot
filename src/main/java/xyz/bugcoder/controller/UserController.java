package xyz.bugcoder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.bugcoder.bean.User;
import xyz.bugcoder.service.UserService;

import java.util.List;

/**
 * Create with IDEA.
 *
 * @Package: xyz.bugcoder.controller
 * @Description:
 * @Date: 2019/9/13 18:43
 * @Author: Wyj
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<User> listUser(){

        return userService.list();
    }

}
