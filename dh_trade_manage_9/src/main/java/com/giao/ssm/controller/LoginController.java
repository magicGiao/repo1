package com.giao.ssm.controller;


import com.giao.ssm.pojo.Usert;
import com.giao.ssm.service.UsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UsertService usertService;

    @RequestMapping("/loginG")
    public String loginG(String uname,String pwd){
        System.out.println(uname+pwd);
        Usert u=usertService.login(uname,pwd);
        System.out.println(u);
        if (u==null){
            System.out.println("登录失败");
            return "redirect:/login/loginTo";
        }
        return "redirect:/login/fmain";
    }


    @RequestMapping("/loginTo")
    public ModelAndView findFacAll(ModelAndView model,String uname,String pwd) {

        model.setViewName("login");
        return model;
    }

    @RequestMapping("/fmain")
    public ModelAndView fmain(ModelAndView model) {
        model.setViewName("/home/fmain");
        return model;
    }

    //系统首页模块
    @RequestMapping("/home.action")
    public String login() {

        return "/login";
    }

    //登录到后台
    @RequestMapping(value = "/fmain.action")
    public String fmain() {
        return "/home/fmain";
    }

    @RequestMapping(value = "/title.action")
    public String title() {
        return "/home/title";
    }

    @RequestMapping(value = "/left.action")
    public String left() {
        return "/home/left";
    }

    @RequestMapping(value = "/main.action")
    public String main() {
        return "/home/olmsgList"; // 首页转向留言板
    }

    // 系统管理模块

    @RequestMapping("/sysadminMain.action")
    public String sysadminMain() {
        return "/sysadmin/main";
    }

    @RequestMapping("/sysadminLeft.action")
    public String sysadminLeft() {
        return "/sysadmin/left";
    }

    // 基础信息模块

    @RequestMapping("/baseinfoMain.action")
    public String baseinfoMain() {
        return "/baseinfo/main";
    }

    @RequestMapping("/baseinfoLeft.action")
    public String baseinfoLeft() {
        return "/baseinfo/left";
    }

    // 货运管理模块

    @RequestMapping("/cargoMain.action")
    public String cargoMain() {
        return "/cargo/main";
    }

    @RequestMapping("/cargoLeft.action")
    public String cargoLeft() {
        return "/cargo/left";
    }
    //统计分析

    @RequestMapping("/statMain.action")
    public String statMain(){
        return "/stat/main";
    }

    @RequestMapping("/statLeft.action")
    public String statLeft(){
        return "/stat/left";
    }
}
