package xyz.bugcoder.controller;

import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.bugcoder.bean.Category;
import xyz.bugcoder.bean.Property;
import xyz.bugcoder.service.CategoryService;
import xyz.bugcoder.service.PropertyService;

import java.util.List;

/**
 * Create with IDEA.
 *
 * @Package: xyz.bugcoder.controller
 * @Description:
 * @Date: 2019/9/16 16:55
 * @Author: Wyj
 */
@RestController
public class PropertyController {

    @Autowired
    PropertyService propertyService;
    @Autowired
    CategoryService categoryService;


    // 一个分类(cid)的属性值显示
    @GetMapping("/properties")
    public List<Property> listProperty(@Param(value = "cid") int cid){

        return propertyService.listByCid(cid);
    }

    @GetMapping("/categories/{cid}/properties")
    public PageInfo<Property> pageList(@PathVariable("cid") int cid,
                                       @RequestParam(value = "start", defaultValue = "0") int start,
                                       @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {

        start = start<0?0:start;
        PageInfo<Property> page = propertyService.pageList(cid, start, size, 5);

        return page;
    }

    @GetMapping("/categories/{cid}")
    public Category getCategory(@PathVariable(value = "cid") int cid){

        return categoryService.get(cid);
    }

    @GetMapping("/properties/{ptid}")
    public Property get(@PathVariable(value = "ptid") int ptid){


        return propertyService.get(ptid);
    }

    @PutMapping("/properties")
    public void update(@RequestBody Property p){

        propertyService.update(p);
    }

    @PostMapping("/properties")
    public void add(@RequestBody Property p){

        propertyService.add(p);
    }

    // 添加 PathVariable(路径变量)注解，而不是@Param
    @DeleteMapping("/properties/{id}")
    public void delete(@PathVariable(value = "id") int id){

        propertyService.delete(id);
    }

}
