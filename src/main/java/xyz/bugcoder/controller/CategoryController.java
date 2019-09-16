package xyz.bugcoder.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.bugcoder.bean.Category;
import xyz.bugcoder.service.CategoryService;

import java.util.List;

/**
 * Create with IDEA.
 *
 * @Package: xyz.bugcoder.controller
 * @Description:
 * @Date: 2019/9/15 21:57
 * @Author: Wyj
 */
@Controller
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping("/admin_category_list")
    public String listCategory(Model m){

        PageHelper.startPage(0, 5, "id desc");
        List<Category> cs = categoryService.list();
        PageInfo<Category> page = new PageInfo<>(cs, 5);
        m.addAttribute("page", page);
        m.addAttribute("cs", cs);

        return "admin/listCategory";
    }

}
