package com.giao.ssm.controller;

import com.giao.ssm.pojo.ExtCproductC;
import com.giao.ssm.pojo.FactoryC;
import com.giao.ssm.service.ExtCproductCService;
import com.giao.ssm.service.FactoryCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/ect")
public class ExtCproductCController {
    @Autowired
    ExtCproductCService extCproductCService;
    @Autowired
    FactoryCService factoryCService;

    @RequestMapping("/findExtAll")
    public  String findExtAll(Map map, String contractProductId,String contractId){
        System.out.println("contractProductId="+contractProductId);
        List<ExtCproductC>e= extCproductCService.findExtAll(contractProductId);
        map.put("dataList",e);
        map.put("contractProductId",contractProductId);
        System.out.println(e);
        List<FactoryC> fac=factoryCService.findFactAll();
        map.put("factoryList",fac);
        map.put("contractId",contractId);
        return "/basicinfo/contract/jExtCproductCreate";
    }
    public String uuid(){

        String s= UUID.randomUUID().toString();
        s=s.replace("-","");
        return s;
    }

    @RequestMapping("/addExt")
    public String addExt(Map map,String contractProductId,ExtCproductC extCproductC,String contractId){
        extCproductC.setExtCproductId(uuid());
        extCproductC.setAmount(extCproductC.getCnumber()*extCproductC.getPrice());
        extCproductCService.addExt(extCproductC);
        System.out.println("进入添加"+extCproductC);
        System.out.println(contractId);
        return findExtAll(map,contractProductId,contractId);
    }

    @RequestMapping("/deleteExt")
    public  String deleteExt(String extCproductId,String contractProductId,String contractId,Map map){
        extCproductCService.deleteExt(extCproductId);
        return findExtAll(map,contractProductId,contractId);
    }

    @RequestMapping("/findExtOne")
    public String findExtOne(String extCproductId,Map map,String contractId){
        ExtCproductC extCproductC= extCproductCService.findExtOne(extCproductId);
        map.put("obj",extCproductC);
        System.out.println(extCproductC);
        List<FactoryC> fac=factoryCService.findFactAll();
        map.put("factoryList",fac);
        map.put("contractId",contractId);
        return "/basicinfo/contract/jExtCproductUpdate";
    }


    @RequestMapping("/updateExt")
    public  String updateExt(ExtCproductC extCproductC,Map map,String contractId){
        extCproductCService.updateExt(extCproductC);
        return  findExtAll(map,extCproductC.getContractProductId(),contractId);
    }

}
