package xyz.bugcoder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.bugcoder.bean.Admin;
import xyz.bugcoder.bean.AdminExample;
import xyz.bugcoder.mapper.AdminMapper;
import xyz.bugcoder.service.AdminService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create with IDEA.
 *
 * @Package: xyz.bugcoder.service.impl
 * @Description:
 * @Date: 2019/9/14 10:55
 * @Author: Wyj
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    @Override
    public List<Admin> list() {

        AdminExample example = new AdminExample();
        example.setOrderByClause("id");
        return adminMapper.selectByExample(example);
    }

    // map存储所有账号，密码，登录验证
    @Override
    public Map<String, String> list2Map() {

        Map<String, String> map = new HashMap<>();
        List<Admin> admins = list();
        for (Admin a : admins) {

            map.put(a.getUsername(), a.getPassword());
        }

        return map;
    }
}
