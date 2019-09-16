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

    @RequestMapping("/admin_property_edit")
    public String editProperty(){

        return "admin/editProperty";
    }

}
