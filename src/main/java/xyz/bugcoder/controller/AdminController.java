package xyz.bugcoder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.bugcoder.bean.Admin;
import xyz.bugcoder.bean.Category;
import xyz.bugcoder.service.AdminService;
import xyz.bugcoder.service.CategoryService;

import javax.servlet.http.HttpSession;
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
    CategoryService categoryService;

    // 跳转到登录页面
    @RequestMapping("/admin")
    public String admin(){

        return "admin/adminLogin";
    }

    // 登录
    @RequestMapping("/admin_login")
    public String login(@RequestParam(value = "adminUser")String adminUser,
                        @RequestParam(value = "adminPass")String adminPass,
                        Model m, HttpSession session
                        ){
        Map<String, String> map = adminService.list2Map();

        Admin admin = adminService.get(adminUser);
        // 登录成功
        if (map.get(adminUser) != null && map.get(adminUser).equals(adminPass)){

            session.setAttribute("admin", admin);
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
