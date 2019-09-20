package xyz.bugcoder.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.bugcoder.bean.Product;
import xyz.bugcoder.service.ProductService;

import java.util.List;

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

    @RequestMapping("/admin_product_list")
    public String listProduct(){

//        PageHelper.startPage(0, 5,"id desc");
//        List<Product> ps = productService.list(cid);
//        PageInfo<Product> page = new PageInfo<>(ps, 3);
//        m.addAttribute("ps", ps);
//        m.addAttribute("page", page);

        return "admin/listProduct";
    }

    // 更新属性已通过ajax完成，这个方法就不需要了，editProperty.html也不需要了
//    @RequestMapping("/admin_property_edit")
//    public String editProperty(){
//
//        return "admin/editProperty";
//    }

}
