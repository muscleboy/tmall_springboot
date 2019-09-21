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

    @GetMapping("/categories/{id}")
    public Category getCategory(@PathVariable(value = "id") int id){

        return categoryService.get(id);
    }

    // 新增分类
    @PostMapping("/categories")
    public void add(Category c, MultipartFile image, HttpServletRequest request) throws IOException {

        categoryService.add(c);
        saveOrUpdateImageFile(c, image, request);
    }

    // 更新分类
    @PutMapping("/categories/{id}")
    public void update(Category c, MultipartFile image, HttpServletRequest request) throws IOException {

        categoryService.update(c);
        saveOrUpdateImageFile(c, image, request);
    }

    // 删除分类，并且删除相应图片
    @DeleteMapping("/categories/{id}")
    public void delete(@PathVariable(value = "id") int id, HttpServletRequest request) throws IOException {

        categoryService.delete(id);
        File path = new File(request.getServletContext().getRealPath("images/category"));
        File file = new File(path, id + ".jpg");
        file.delete();
    }

    // 实体类不需要加上@RequestBody注解，加上会出错...
    public void saveOrUpdateImageFile(Category c, MultipartFile file, HttpServletRequest request)
            throws IOException {

        // 获取到的路径是webapp(需要自己创建)下的，放在static下获取不到路径，有莫名的Bug
        File imageFolder= new File(request.getServletContext().getRealPath("images/category"));
        // mybatis generator生成的时候，table属性没有设置generatedKey
        // 导致add之后，取到的id为空
        File image = new File(imageFolder,c.getId() + ".jpg");
        if(!image.getParentFile().exists())
            image.getParentFile().mkdirs();
        file.transferTo(image);
    }
}
