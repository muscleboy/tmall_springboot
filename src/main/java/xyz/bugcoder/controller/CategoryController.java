package xyz.bugcoder.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/categories")
    public PageInfo<Category> listCategory(@RequestParam(value = "start", defaultValue = "0") int start,
                                           @RequestParam(value = "size", defaultValue = "5") int size
                                       ){

        start = start < 0 ? 0 : start;
        PageHelper.startPage(start, size, "id desc");
        List<Category> cs = categoryService.list();
        PageInfo<Category> page = new PageInfo<>(cs, 5);
        return page;
    }

//    @GetMapping("/categories/{cid}")
//    public Category getCategory(@PathVariable(value = "cid") int cid){
//
//        return categoryService.get(cid);
//    }

}
