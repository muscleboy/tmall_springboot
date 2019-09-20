package xyz.bugcoder.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.bugcoder.bean.Category;
import xyz.bugcoder.service.CategoryService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
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

    @PostMapping("/categories")
    public void add(Category c, MultipartFile image, HttpServletRequest request) throws IOException {

        categoryService.add(c);
        saveOrUpdateImageFile(c, image, request);
    }

    public void saveOrUpdateImageFile(Category c, MultipartFile image, HttpServletRequest request)
            throws IOException {

        System.out.println(c.getId());
        System.out.println(c.getName());
        File imageFolder= new File(request.getServletContext().getRealPath("images/category"));
        File file = new File(imageFolder,c.getId() + ".jpg");
        if(!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        image.transferTo(file);

    }
}
