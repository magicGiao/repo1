package com.giao.ssm.controller;

import com.giao.ssm.mapper.FactoryCMapper;
import com.giao.ssm.pojo.ContractProductC;
import com.giao.ssm.pojo.FactoryC;
import com.giao.ssm.service.ContractProductCService;
import com.giao.ssm.service.ExtCproductCService;
import com.giao.ssm.service.FactoryCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/pro")
public class ContractProductCController {

@Autowired
    ContractProductCService contractProductCService;
@Autowired
    FactoryCService factoryCService;
@Autowired
    ExtCproductCService extCproductCService;


@RequestMapping("addProTo")
public  String addProTo(Map map,String contractId){
    return find(map,contractId);
}

public String uuid(){

   String s= UUID.randomUUID().toString();
   s=s.replace("-","");
   return s;
}
@RequestMapping("/addPro")

    public  String addPro(ContractProductC contractProductC,Map map){
        contractProductC.setContractProductId(uuid());
         System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+contractProductC);
        System.out.println(contractProductC);
         contractProductC.setAmount(contractProductC.getCnumber()*contractProductC.getPrice());
        contractProductCService.addPro(contractProductC);
        find(map,contractProductC.getContractId());
    return "redirect:/pro/addProTo";
}



    public String find(Map map,String contractId){
        List<ContractProductC> pro=contractProductCService.findProAll(contractId);
        map.put("dataList",pro);
        map.put("contractId",contractId);
        System.out.println(pro);
        List<FactoryC> fac=factoryCService.findFactAll();
        map.put("factoryList",fac);
        return "/basicinfo/contract/jContractProductCreate";
    }


    @RequestMapping("/deletePro")
    public String deletePro(Map map,String[] contractProductId,String contractId){
        extCproductCService.deleteExtAll(contractProductId);
        contractProductCService.deletePro(contractProductId);
        return find(map,contractId);
    }

    @RequestMapping("/updateProTo")

    public  String updateTo(String contractProductId,Map map,String contractId){
        ContractProductC c=contractProductCService.findProOne(contractProductId);
        System.out.println(c);
        map.put("pro",c);
        List<FactoryC> fac=factoryCService.findFactAll();
        map.put("factoryList",fac);
        map.put("contractId",contractId);
        return "/basicinfo/contract/jContractProductUpdate";
    }


    @RequestMapping("/updatePro")
    public String updatePro(Map map ,String contractId,ContractProductC contractProductC){
        System.out.println("》》》修改"+contractProductC.getContractProductId());
        System.out.println(contractProductC.getProductImage());
        System.out.println(contractProductC.getOrderNo());
        contractProductC.setAmount(contractProductC.getCnumber()*contractProductC.getPrice());
        contractProductCService.updatePro(contractProductC);

        return  find(map,contractId);
    }
}
