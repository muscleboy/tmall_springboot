package xyz.bugcoder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Package: xyz.bugcoder.controller
 * @author: Weiyj
 * @Description: TODO
 * @createTime 2019-09-16 10:29
 */
@Controller
public class AdminPageController {

    @RequestMapping("/admin_category_list")
    public String listCategory(){

        return "admin/listCategory";
    }

    @RequestMapping("/admin_property_list")
    public String listProperty(){

        return "admin/listProperty";
    }

    @RequestMapping("/admin_user_list")
    public String user(){

        return "admin/listUser";
    }

    @RequestMapping("/admin_order_list")
    public String order(){

        return "admin/listOrder";
    }

    @RequestMapping("/admin_category_edit")
    public String editCategory(){

        return "admin/editCategory";
    }

    // 更新属性已通过ajax完成，这个方法就不需要了，editProperty.html也不需要了
//    @RequestMapping("/admin_property_edit")
//    public String editProperty(){
//
//        return "admin/editProperty";
//    }

}
