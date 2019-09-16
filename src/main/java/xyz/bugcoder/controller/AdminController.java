package xyz.bugcoder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.bugcoder.service.AdminService;
import xyz.bugcoder.service.ProductService;

import java.util.Map;

/**
 * Create with IDEA.
 *
 * @Package: xyz.bugcoder.controller
 * @Description:
 * @Date: 2019/9/14 10:36
 * @Author: Wyj
 */
@Controller
public class AdminController {

    @Autowired
    AdminService adminService;
    @Autowired
    ProductService productService;

    // 跳转到登录页面
    @RequestMapping("/admin")
    public String admin(){

        return "admin/adminLogin";
    }

    // 登录
    @RequestMapping("/admin_login")
    public String login(@RequestParam(value = "adminUser")String adminUser,
                        @RequestParam(value = "adminPass")String adminPass,
                        Model m
                        ){
        Map<String, String> map = adminService.list2Map();

        // 登录成功
        if (map.get(adminUser) != null && map.get(adminUser).equals(adminPass)){

            return "redirect:/admin_category_list";
            // 登录失败
        }else {

            return "redirect:/admin_login_err";
        }
    }

    // 登录失败
    @RequestMapping("/admin_login_err")
    public String login_err(){

        return "admin/adminLogin";
    }
}
