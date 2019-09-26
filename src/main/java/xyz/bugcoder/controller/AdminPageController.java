package xyz.bugcoder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.bugcoder.bean.Admin;
import xyz.bugcoder.service.ProductService;

import javax.servlet.http.HttpSession;

/**
 * @Package: xyz.bugcoder.controller
 * @author: Weiyj
 * @Description: TODO
 * @createTime 2019-09-16 10:29
 */
@Controller
public class AdminPageController {

    @Autowired
    ProductService productService;

    @RequestMapping("/admin_category_list")
    public String listCategory(HttpSession session){

        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null){

            return "redirect:/admin";
        }
        return "admin/listCategory";
    }

    @RequestMapping("/admin_property_list")
    public String listProperty(HttpSession session){

        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null){

            return "redirect:/admin";
        }
        return "admin/listProperty";
    }

    @RequestMapping("/admin_user_list")
    public String user(HttpSession session){

        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null){

            return "redirect:/admin";
        }
        return "admin/listUser";
    }

    @RequestMapping("/admin_order_list")
    public String order(HttpSession session){

        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null){

            return "redirect:/admin";
        }
        return "admin/listOrder";
    }

    @RequestMapping("/admin_category_edit")
    public String editCategory(HttpSession session){

        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null){

            return "redirect:/admin";
        }
        return "admin/editCategory";
    }

    @RequestMapping("/admin_product_list")
    public String listProduct(HttpSession session){

        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null){

            return "redirect:/admin";
        }

        return "admin/listProduct";
    }

    @RequestMapping("/admin_product_edit")
    public String editProduct(HttpSession session){

        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null){

            return "redirect:/admin";
        }
        return "admin/editProduct";
    }

    @RequestMapping("/admin_productImage_list")
    public String listProductImage(HttpSession session){

        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null){

            return "redirect:/admin";
        }
        return "admin/listProductImage";
    }

    @RequestMapping("/admin_propertyValue_edit")
    public String listPropertyValue(HttpSession session){

        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null){

            return "redirect:/admin";
        }
        return "admin/editPropertyValue";
    }

    // 更新属性已通过ajax完成，这个方法就不需要了，editProperty.html也不需要了
//    @RequestMapping("/admin_property_edit")
//    public String editProperty(){
//
//        return "admin/editProperty";
//    }

}
