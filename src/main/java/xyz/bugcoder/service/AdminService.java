package xyz.bugcoder.service;

import xyz.bugcoder.bean.Admin;

import java.util.List;
import java.util.Map;

/**
 * Create with IDEA.
 *
 * @Package: xyz.bugcoder.service
 * @Description:
 * @Date: 2019/9/14 10:54
 * @Author: Wyj
 */
public interface AdminService {

    Admin get(String username);
    List<Admin> list();
    Map<String, String> list2Map();

}
