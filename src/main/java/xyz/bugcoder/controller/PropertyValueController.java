package xyz.bugcoder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import xyz.bugcoder.bean.Product;
import xyz.bugcoder.bean.PropertyValue;
import xyz.bugcoder.service.ProductService;
import xyz.bugcoder.service.PropertyValueService;

import java.util.List;

/**
 * Create with IDEA.
 *
 * @Package: xyz.bugcoder.controller
 * @Description:
 * @Date: 2019-09-22 10:45
 * @Author: Wyj
 */
@RestController
public class PropertyValueController {

    @Autowired
    PropertyValueService propertyValueService;
    @Autowired
    ProductService productService;

    @GetMapping("/products/{pid}/propertyValues")
    public List<PropertyValue> listPropertyValues(@PathVariable("pid")int pid){

        Product p = productService.get(pid);
        propertyValueService.init(p);
        return propertyValueService.list(pid);
    }

}
