package com.endlessway.controller;

import com.endlessway.pojo.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    /**
     * 测试mvc
     * @return
     */
    @GetMapping("/mvc")
    public String mvc(){
        return "index";
    }

    /**
     * 转发
     * @return
     */
    @GetMapping("/forward")
    public ModelAndView forward(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("forward:/WEB-INF/views/index.jsp");
        return modelAndView;
    }

    /**
     * 重定向
     * @return
     */
    @GetMapping("/redirect")
    public ModelAndView redirect(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/index.jsp");
        return modelAndView;
    }

    /**
     * 响应
     */
    @GetMapping("/res")
    public void res(HttpServletResponse response) throws IOException {
        response.getWriter().print("hello");
    }


    @GetMapping("/json")
    @ResponseBody
    public SysUser json() throws IOException {
        SysUser  user =  new SysUser();
        user.setUsername("endlessway");
        user.setEmail("endlessway999@163.com");
        user.setPassword("endlessway");
        user.setSalt(UUID.randomUUID().toString());
        return user;
    }

    @GetMapping("/converter")
    @ResponseBody
    public String converter(@RequestParam("date") Date date) throws IOException {
       log.info(date.toString());
        return "OK";
    }

    @GetMapping("/header")
    @ResponseBody
    public String header(@RequestHeader(value = "token",required = true) String headerValue,HttpServletResponse response) throws IOException {
        log.info(headerValue);
        Cookie cookie = new Cookie("JSESSIONID",headerValue);
        response.addCookie(cookie);
        return "OK";
    }

    @GetMapping("/cookie")
    @ResponseBody
    public String cookie(@CookieValue(value = "JSESSIONID",required = true) String jsessionid) throws IOException {
        log.info(jsessionid);
        return "OK";
    }
}
