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
 * Create with IDEA.
 *
 * @Package: xyz.bugcoder.controller
 * @Description:
 * @Date: 2019-09-23 20:04
 * @Author: Wyj
 */
@Controller
public class ForeController {

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
    @Autowired
    OrderService orderService;
    @Autowired
    OrderItemService orderItemService;

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

    // 注册
    @RequestMapping("/register")
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

    // 登录
    @RequestMapping("/login")
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

    // 注销登录
    @RequestMapping("/to_logout")
    public String logout(HttpSession session){

        session.removeAttribute("user");

        return "redirect:to_login";
    }

    // 我的订单
    @RequestMapping("/order")
    public String order(HttpSession session, Model model){

        // 获取用户, 未登录就重定向到登录页面
        User user = (User) session.getAttribute("user");
        if (user == null){

            return "redirect:to_login";
        }
        // 通过uid和delete获取orders
        List<Order> os = orderService.listByUid(user.getId());
        // 为订单填充订单项
        orderItemService.fill(os);
        for (Order o : os) {

            System.out.println(o.getOrderItemList());
        }
        model.addAttribute("os", os);

        return "fore/order";
    }

    // 我的购物车
    @RequestMapping("/cart")
    public String cart(HttpSession session, Model m){

        // 获取用户, 未登录就重定向到登录页面
        User user = (User) session.getAttribute("user");
        if (user == null){

            return "redirect:to_login";
        }

        List<OrderItem> ois = orderItemService.listByUser(user.getId());
        m.addAttribute("ois", ois);

        return "fore/cart";
    }

    // 立即购买
    @RequestMapping("/foreBuyNow")
    public String buyNow(HttpSession session, Model m, int pid){

        // 获取用户, 未登录就重定向到登录页面
        User user = (User) session.getAttribute("user");
        if (user == null){

            return "redirect:to_login";
        }

        List<OrderItem> ois = orderItemService.listByUser(user.getId());
        m.addAttribute("ois", ois);

        return "fore/buyNow";
    }

}
