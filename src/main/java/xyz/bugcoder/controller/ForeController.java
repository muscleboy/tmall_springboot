package xyz.bugcoder.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.bugcoder.bean.*;
import xyz.bugcoder.service.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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

    @RequestMapping("checkLoginAjax")
    @ResponseBody
    public String checkLogin(HttpSession session){

        User user = (User) session.getAttribute("user");
        if (user != null)
            return "success";
        return "fail";
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
    public String buyNow(HttpSession session, Model m, int pid, int num){

        // 获取用户, 未登录就重定向到登录页面
        User user = (User) session.getAttribute("user");
        if (user == null){

            return "redirect:to_login";
        }

        Product p = productService.get(pid);
        List<OrderItem> ois = orderItemService.listByUser(user.getId());
        boolean found = false;
        int oiid = 0;
        // 遍历用户的订单项，如果找到就数量+1，update
        for (OrderItem oi : ois) {

            if (oi.getProduct().getId().intValue() == p.getId().intValue()){

                oi.setNumber(oi.getNumber() + num);
                orderItemService.update(oi);
                oiid = oi.getId();
                found = true;
                break;
            }
        }

        // 如果没找到用户的订单项，就创建新的订单项，add
        if (!found){

            OrderItem oi = new OrderItem();
            oi.setNumber(num);
            oi.setPid(pid);
            oi.setUid(user.getId());
            orderItemService.add(oi);
            oiid = oi.getId();
        }

//        m.addAttribute("ois", ois);

        return "redirect:forebuy?oiid=" + oiid;
    }

    // 结算，不论是立即购买，还是购物车结算
    @RequestMapping("/forebuy")
    public String buy(HttpSession session, String[] oiid, Model m){

        List<OrderItem> ois = new ArrayList<>();
        float total = 0;

        for (String s : oiid) {

//            System.out.println(s);
            // "1"  -->  1
            int id = Integer.parseInt(s);
            // 根据oiid获取订单项
            OrderItem oi = orderItemService.get(id);
            // 订单项总价钱
            total += oi.getProduct().getPromotePrice() * oi.getNumber();
            ois.add(oi);
        }

        m.addAttribute("total", total);
        m.addAttribute("ois", ois);
        session.setAttribute("ois", ois);

        return "fore/buyNow";
    }

    // 加入购物车
    @RequestMapping("/foreaddToCart")
    @ResponseBody
    public String addToCart(int pid, int num, HttpSession session){

        Product product = productService.get(pid);
        User user = (User) session.getAttribute("user");

        boolean found = false;
        List<OrderItem> ois = orderItemService.listByUser(user.getId());
        for (OrderItem oi : ois) {
            if (oi.getProduct().getId().intValue() == product.getId().intValue()) {

                oi.setNumber(oi.getNumber() + num);
                orderItemService.update(oi);
                found = true;
                break;
            }
        }

        if (!found) {

            OrderItem oi = new OrderItem();
            oi.setUid(user.getId());
            oi.setPid(pid);
            oi.setNumber(num);
            orderItemService.add(oi);
        }

        return "success";
    }

    // 结算页面提交订单，创建订单
    @RequestMapping("/forecreateOrder")
    public String createOrder(HttpSession session, Order o, Model m){

        User u = (User) session.getAttribute("user");
        // 订单号: 当前时间戳 + 4位随机数
        String orderCode = new SimpleDateFormat("yyyyMMddhhssmm")
                .format(new Date()) + new Random().nextInt(10000);
        // 订单创建时间
        o.setCreateDate(new Date());
        o.setOrderCode(orderCode);
        o.setUid(u.getId());
        // 订单状态为 待支付
        o.setStatus(OrderService.waitPay);

        List<OrderItem> ois = (List<OrderItem>) session.getAttribute("ois");
        float total = orderService.getOrderTotal(o, ois);
//        System.out.println(total);

        session.setAttribute("oid", o.getId());
        session.setAttribute("total", total);

        // 重定向到支付页面，ForePageController负责转发到 pay(支付页面)
        return "redirect:pay?oid=" + o.getId() + "&total=" + total;
    }

    // 确认支付
    @RequestMapping("/forepayed")
    public String payed(int oid, Model m){

        Order o = orderService.get(oid);
        // 订单支付时间
        o.setPayDate(new Date());
        // 订单状态为 待发货
        o.setStatus(OrderService.waitDelivery);
        // 更新订单
        orderService.update(o);
        m.addAttribute("o", o);

        return "fore/payed";
    }

}
