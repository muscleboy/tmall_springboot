package xyz.bugcoder.service;

import xyz.bugcoder.bean.User;

import java.util.List;

/**
 * Create with IDEA.
 *
 * @Package: xyz.bugcoder.service
 * @Description:
 * @Date: 2019/9/13 18:33
 * @Author: Wyj
 */
public interface UserService {

    // 删除，更改用户暂不提供
    void add(User u);
    User get(int id);
    List<User> list();

}
