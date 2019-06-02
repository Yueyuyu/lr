package com.lr.controller;

import com.lr.domain.User;
import com.lr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
//控制类，控制页面跳转，数据传输
public class UserController {
    //自动注入userService，处理业务
    @Autowired
    private UserService userService;

    //跳转到主页
    @RequestMapping("")
    public String index(HttpServletResponse response) {
        //重定向到 /index
        return response.encodeRedirectURL("/index");
    }

    @RequestMapping("/index")
    public String home(Model model) {
        return "index";
    }

    //进入注册页面
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerGet() {
        return "register";
    }

    //注册用户,传输数据
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPost(/*Model model,
                               @ModelAttribute(value = "user")*/ User user,
                               HttpServletResponse response) {
        //使用userService处理业务
        String result = userService.register(user);
        //结果放到model里面，在模板中能够取到model中的值
        /*model.addAttribute("result", result);*/
        return response.encodeRedirectURL("/index");
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGet() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map loginPost(/*Model model,
                            @ModelAttribute(value = "user") */User user,
                            HttpServletResponse response,
                            HttpSession session) {
        System.out.println(session.getId());
        String result = userService.login(user);
        if (result.equals("登陆成功")) {
            //添加到session中，当session中的user存在时判定用户存在
            session.setAttribute("user",user);
            Cookie cookie =new Cookie("user",user.getUsername());
            response.addCookie(cookie);
        }
        //model.addAttribute("result", result);
        Map map = new HashMap<String,String>();
        map.put("code","1");
        map.put("msg","登陆成功");
        map.put("data",user);
        return map;
        //return response.encodeRedirectURL("/index");
    }
}
