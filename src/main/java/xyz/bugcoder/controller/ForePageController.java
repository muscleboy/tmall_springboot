package xyz.bugcoder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.bugcoder.bean.*;
import xyz.bugcoder.service.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Package: xyz.bugcoder.controller
 * @author: Weiyj
 * @Description: TODO
 * @createTime 2019-09-23 09:43
 */
@Controller
public class ForePageController {

    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @Autowired
    ProductImageService productImageService;
    @Autowired
    ReviewService reviewService;
    @Autowired
    PropertyService propertyService;
    @Autowired
    PropertyValueService propertyValueService;
    @Autowired
    UserService userService;

    // 首页
    @RequestMapping("/forehome")
    public String home(Model m){

        List<Category> cs = categoryService.list();
        productService.fill(cs);

        m.addAttribute("cs", cs);

        return "fore/home";
    }

    // 产品页
    @RequestMapping("/foreproduct")
    public String foreproduct(int pid, Model m){

        Product p = productService.get(pid);
        List<ProductImage> singleImages = productImageService.listSingleImages(pid);
        List<ProductImage> detailImages = productImageService.listDetailImages(pid);
        List<Review> rs = reviewService.list(pid);
        List<Property> pts = propertyService.listByCid(p.getCid());
        List<PropertyValue> pvs = propertyValueService.list(pid);

        m.addAttribute("p", p);
        m.addAttribute("singleImages", singleImages);
        m.addAttribute("detailImages", detailImages);
        m.addAttribute("rs", rs);
        m.addAttribute("pts", pts);
        m.addAttribute("pvs", pvs);

        return "fore/product";
    }

    @RequestMapping("/to_register")
    public String to_register(){

        return "fore/register";
    }

    @RequestMapping("register")
    public String register(Model model, User user){

        String name = user.getName();
        user.setName(name);
        if (userService.isExist(name)){

            String msg = "该用户名已被使用，请换一个";
            model.addAttribute("msg", msg);
            model.addAttribute("user", null);
            return "fore/register";
        }

        userService.add(user);
        return "redirect:to_login";
    }

    @RequestMapping("/to_login")
    public String regito_loginster(){

        return "fore/login";
    }

    @RequestMapping("login")
    public String login(@RequestParam("name") String name,
                        @RequestParam("password") String password,
                        Model model, HttpSession session){

        User user = userService.get(name, password);
        String msg = "账号或者密码错误";
        if (user == null){

            model.addAttribute("msg", msg);
            return "redirect:to_login";
        }

        session.setAttribute("user", user);
        return "redirect:forehome";
    }

}
