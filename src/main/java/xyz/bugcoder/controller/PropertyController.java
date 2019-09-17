package xyz.bugcoder.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.bugcoder.bean.Property;
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


    // 一个分类(cid)的属性值显示
    @GetMapping("/properties")
    public List<Property> listProperty(@Param(value = "cid") int cid){

        return propertyService.listByCid(cid);
    }

    @GetMapping("/properties/{ptid}")
    public Property get(@PathVariable(value = "ptid") int ptid){


        return propertyService.get(ptid);
    }

    @PutMapping("/properties")
    public void update(@RequestBody Property p){

        propertyService.update(p);
    }

}
