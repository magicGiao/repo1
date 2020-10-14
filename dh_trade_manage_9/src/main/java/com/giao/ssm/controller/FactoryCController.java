package com.giao.ssm.controller;


import com.giao.ssm.pojo.FactoryC;
import com.giao.ssm.service.FactoryCService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/fac")
public class FactoryCController {
    @Autowired
    FactoryCService factoryCService;






   @RequestMapping("/findFacAll")
    public String findFacAll(@RequestParam(value = "pn",defaultValue = "1")Integer pn, Map map) {
       PageHelper.startPage(pn,3);
        List<FactoryC> fac = factoryCService.findFactAll();
       PageInfo pages = new PageInfo(fac,5);
        map.put("dataList", pages);
        return "basicinfo/factory/jFactoryList";
    }

    @RequestMapping("/addFacTo")
    public String addFacTo() {

        return "basicinfo/factory/jFactoryCreate";
    }

    public String uuid() {
        String s = UUID.randomUUID().toString();
        s = s.replace("-", "");
        return s;
    }

    @RequestMapping("/addFac")
    public String addFac(FactoryC factoryC){
        System.out.println(factoryC+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        factoryC.setFactoryId(uuid());
        factoryCService.addFact(factoryC);
        return "redirect:/fac/findFacAll";
    }

    @RequestMapping("/deleteByid")
    public String deleteByid(  @RequestParam("factoryId") String[] factoryIds){
//        for (int i=0; i<factoryIds.length;i++){
        System.out.println("factoryid"+factoryIds);
        factoryCService.deleteByid(factoryIds);
//        }
        return "redirect:/fac/findFacAll";

    }
    @RequestMapping("/stop")
    public String stop(String[] factoryId){
//       for (int i=0;i<factoryId.length;i++){
//        System.out.println("停用"+factoryId);
        factoryCService.stop(factoryId);
//        }
        return "redirect:/fac/findFacAll";

    }

    @RequestMapping("/start")
    public String start(String[] factoryId){
//       for (int i=0;i<factoryId.length;i++){
        System.out.println("启用"+factoryId);
        factoryCService.start(factoryId);
//       }
        return "redirect:/fac/findFacAll";

    }

    @RequestMapping("/findFactOne")
    public String findFactOne(String factoryId,Map map){
        FactoryC factoryC1=factoryCService.findFactOne(factoryId);
        map.put("obj",factoryC1);
        return "basicinfo/factory/jFactoryView";
    }
    @RequestMapping("/updateto")
    public String updateto(String factoryId,Map map){
        System.out.println(factoryId);
        FactoryC factoryC1=factoryCService.findFactOne(factoryId);
        map.put("obj",factoryC1);
        return "basicinfo/factory/jFactoryUpdate";
    }
    @RequestMapping("/updateFac")
    public String updateFac(FactoryC factoryC){
        System.out.println(factoryC);
        System.out.println(factoryC.getFactoryId());

        factoryCService.updateFac(factoryC);
        return "redirect:/fac/findFacAll";
    }

}
