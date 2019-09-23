package xyz.bugcoder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.bugcoder.bean.User;
import xyz.bugcoder.bean.UserExample;
import xyz.bugcoder.mapper.UserMapper;
import xyz.bugcoder.service.UserService;

import java.util.List;


/**
 * Create with IDEA.
 *
 * @Package: xyz.bugcoder.service.impl
 * @Description:
 * @Date: 2019/9/13 18:33
 * @Author: Wyj
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public void add(User u) {

        userMapper.insert(u);
    }

    @Override
    public User get(int id) {

        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> list() {

        UserExample example = new UserExample();
        example.setOrderByClause("id desc");
        return userMapper.selectByExample(example);
    }

    @Override
    public boolean isExist(String name) {
        UserExample example = new UserExample();
        example.createCriteria()
                .andNameEqualTo(name);
        List<User> result = userMapper.selectByExample(example);
        if (!result.isEmpty())
            return true;
        return false;
    }

    @Override
    public User get(String username, String password) {
        UserExample example = new UserExample();
        example.createCriteria()
                .andNameEqualTo(username)
                .andPasswordEqualTo(password);
        List<User> users = userMapper.selectByExample(example);

        if (users.isEmpty())
            return null;
        return users.get(0);
    }
}
