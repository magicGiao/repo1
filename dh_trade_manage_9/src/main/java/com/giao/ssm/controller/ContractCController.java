package com.giao.ssm.controller;

import com.giao.ssm.pojo.*;
import com.giao.ssm.service.*;
import com.giao.ssm.util.ContractPrint;
import com.giao.ssm.util.ContractPrintMB;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/con")
public class ContractCController {

    @Autowired
    FactoryCService factoryCService;
    @Autowired
    ContractCService contractCService;
    @Autowired
    ContractProductCService Pro;

    @Autowired
    ExtCproductCService ext;

    @Autowired
    OutProductService outProductService;

    @RequestMapping("/findConAll")
    public String findConAll(@RequestParam(value = "pn",defaultValue = "1")Integer pn, Map map) {
//        List<ContractC> con = contractCService.findConAll();
//        for (ContractC c :
//                con) {
//            int fj=0;
//            String contractId = c.getContractId();
//            Double zj = 0.0;
//            List<ContractProductC> cc = Pro.findProZj(contractId);
//            int hw = cc.size();
//            for (int i = 0; i < cc.size(); i++) {
//                if (cc.get(i).getAmount() != 0) {
//                    System.out.println("进入赋值1");
//                    zj += cc.get(i).getAmount();
//                }
//            }
//            List<ContractProductC> pro = Pro.findProOneDelete(contractId);
//            for (int p = 0; p < pro.size(); p++) {
//                List<ExtCproductC> e = ext.findExtZj(pro.get(p).getContractProductId());
//                fj+=e.size();
//                for (int ee = 0; ee < e.size(); ee++) {
//                    if (e.get(ee).getAmount()!=0){
//                        System.out.println("进入赋值2");
//                        zj += e.get(ee).getAmount();
//                    }
//                }
//            }
//            c.setTotalAmount(zj);
//            c.setHw(hw);
//            c.setFj(fj);
//
//            ContractC contractC = new ContractC();
//            contractC.setContractId(contractId);
//            contractC.setTotalAmount(zj);
//            contractCService.updateCon(contractC);
//        }
        PageHelper.startPage(pn,3);
        List<ContractC> con = contractCService.findConAll();
        for (int a = 0; a < con.size(); a++) {
            int fj = 0;
            Double zj = 0.0;
            List<ContractProductC> c = Pro.findProZj(con.get(a).getContractId());
            int hw = c.size();
            for (int i = 0; i < c.size(); i++) {
                if (c.get(i).getAmount() != 0) {
                    zj += c.get(i).getAmount();
                }
            }
            System.out.println(zj);
            List<ContractProductC> pro = Pro.findProOneDelete(con.get(a).getContractId());
            for (int p = 0; p < pro.size(); p++) {
                List<ExtCproductC> e = ext.findExtZj(pro.get(p).getContractProductId());
                fj += e.size();
                for (int ee = 0; ee < e.size(); ee++) {
                    if (e.get(ee).getAmount() != 0) {
                        zj += e.get(ee).getAmount();
                    }
                }
            }
            if (zj != con.get(a).getTotalAmount()) {
                ContractC contractC = new ContractC();
                contractC.setContractId(con.get(a).getContractId());
                contractC.setTotalAmount(zj);
                contractCService.updateCon(contractC);
            }
            java.math.BigDecimal b = new java.math.BigDecimal(zj);
            con.get(a).setTotalAmount(zj );
            con.get(a).setCpnum(hw);
            con.get(a).setExtnum(fj);
        }

        PageInfo pages = new PageInfo(con,5);
//        PageInfo<ContractC> contractCPageInfo = new PageInfo<>(con);
        map.put("dataList",pages);
        return "basicinfo/contract/jContractList";
    }

    @RequestMapping("/findConOne")
    public String findConOne(Map map, String contractId) {
        ContractC contractC = contractCService.findConOne(contractId);
        map.put("obj", contractC);

        List<ContractProductC> c = Pro.findProAll(contractId);
        map.put("dataListC",c);
        List<ExtCproductC> ee = new ArrayList<ExtCproductC>();
        for (int i2 = 0; i2 < c.size(); i2++) {
            List<ExtCproductC> e = ext.findExtAll(c.get(i2).getContractProductId());
            for (int i = 0; i < e.size(); i++) {
                ee.add(e.get(i));
            }
        }
        System.out.println("赋值后" + ee.size());
        map.put("dataListE",ee);
        List<FactoryC> fac = factoryCService.findFactAll();
        map.put("factoryList", fac);
        return "basicinfo/contract/jContractView";
    }

    @RequestMapping("/deleteCon")
    public String deleteCon(String[] contractId) {

        for (int i = 0; i < contractId.length; i++) {
//            System.out.println(contractId[i]);
            List<ContractProductC> c = Pro.findProOneDelete(contractId[i]);
            for (int ii = 0; ii < c.size(); ii++) {
                String cid[] = {c.get(ii).getContractProductId()};
                ext.deleteExtAll(cid);
                System.out.println("删除ext" + cid);
                Pro.deletePro(cid);
                System.out.println("删除pro" + cid);
            }
        }
        contractCService.deleteCon(contractId);
        System.out.println("删除con" + contractId);
        return "redirect:/con/findConAll";
    }

    //<!--    修改为归档-->
    @RequestMapping("/updateConGui")
    public String updateConGui(String[] contractId) {
        for (int i = 0; i < contractId.length; i++) {
            contractCService.updateConGui(contractId[i]);
        }
        return "redirect:/con/findConAll";
    }

    //<!--    修改为草稿-->
    @RequestMapping("/updateConCao")
    public String updateConCao(String[] contractId) {
        for (int i = 0; i < contractId.length; i++) {
            contractCService.updateConCao(contractId[i]);
        }
        return "redirect:/con/findConAll";
    }

    //<!--    修改为待报运-->
    @RequestMapping("/updateConDai")
    public String updateConDai(String[] contractId) {
        for (int i = 0; i < contractId.length; i++) {
            contractCService.updateConDai(contractId[i]);
        }
        return "redirect:/con/findConAll";
    }

    @RequestMapping("/addTo")
    public String addTo() {

        return "basicinfo/contract/jContractCreate";
    }

    public String uuid() {
        String s = UUID.randomUUID().toString();
        s = s.replace("-", "");
        return s;
    }

    @RequestMapping("/addConTo")
    public String addConTo(ContractC contractC) {
        contractC.setContractId(uuid());
        System.out.println(contractC);

        System.out.println(contractC.getSigningDate());
        contractCService.addCon(contractC);
        return "redirect:/con/findConAll";
    }

    @RequestMapping("/updateTo")
    public String updateTo(Map map, String contractId) {
        ContractC contractC = contractCService.findConOne(contractId);
        System.out.println(contractC);
        map.put("obj", contractC);
        return "basicinfo/contract/jContractUpdate";
    }

    @RequestMapping("/updateCon")
    public String updateCon(ContractC contractC) {
        System.out.println(contractC);

        contractCService.updateCon(contractC);

        return "redirect:/con/findConAll";
    }

    @RequestMapping("/cx")
    public String cx(@RequestParam(value = "pn",defaultValue = "1")Integer pn,Map map){
        PageHelper.startPage(pn,3);
        List<ContractC> con = contractCService.findConAll();
        for (int a = 0; a < con.size(); a++) {
            int fj = 0;
            Double zj = 0.0;
            List<ContractProductC> c = Pro.findProZj(con.get(a).getContractId());
            int hw = c.size();
            for (int i = 0; i < c.size(); i++) {
                if (c.get(i).getAmount() != 0) {
                    zj += c.get(i).getAmount();
                }
            }
            System.out.println(zj);
            List<ContractProductC> pro = Pro.findProOneDelete(con.get(a).getContractId());
            for (int p = 0; p < pro.size(); p++) {
                List<ExtCproductC> e = ext.findExtZj(pro.get(p).getContractProductId());
                fj += e.size();
                for (int ee = 0; ee < e.size(); ee++) {
                    if (e.get(ee).getAmount() != 0) {
                        zj += e.get(ee).getAmount();
                    }
                }
            }
            if (zj != con.get(a).getTotalAmount()) {
                ContractC contractC = new ContractC();
                contractC.setContractId(con.get(a).getContractId());
                contractC.setTotalAmount(zj);
                contractCService.updateCon(contractC);
            }
            con.get(a).setTotalAmount(zj);
            con.get(a).setCpnum(hw);
            con.get(a).setExtnum(fj);
        }
        PageInfo pages = new PageInfo(con,5);
        map.put("dataList", pages);
        return "basicinfo/contract/jContractListCx";

    }   



    /**
     * 打印
     */
@SneakyThrows
@RequestMapping("/dy")
    public void dy(String contractId, HttpServletRequest request, HttpServletResponse response){
    System.out.println(contractId);
    ContractPrint cp = new ContractPrint();
    ContractC obj = outProductService.view(contractId);
    System.out.println(obj);
    cp.print(obj, request.getSession().getServletContext().getRealPath("/"), response);
}

@SneakyThrows
@RequestMapping("/dyMB")
    public void dyMB(String contractId, HttpServletRequest request, HttpServletResponse response){
    System.out.println(contractId);
    ContractPrintMB cp = new ContractPrintMB();
    ContractC obj = outProductService.view(contractId);
    System.out.println(obj);
    cp.print(obj, request.getSession().getServletContext().getRealPath("/"), response);
}
}
