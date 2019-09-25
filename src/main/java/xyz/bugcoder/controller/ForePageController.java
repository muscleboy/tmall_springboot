package xyz.bugcoder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Package: xyz.bugcoder.controller
 * @author: Weiyj
 * @Description: TODO
 * @createTime 2019-09-23 09:43
 */
@Controller
public class ForePageController {

    @RequestMapping("/to_register")
    public String to_register(){

        return "fore/register";
    }

    @RequestMapping("/to_login")
    public String to_loginster(){

        return "fore/login";
    }

    @RequestMapping("/pay")
    public String to_pay(){

        return "fore/pay";
    }

}
