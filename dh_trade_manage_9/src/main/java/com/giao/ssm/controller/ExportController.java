package com.giao.ssm.controller;


import com.giao.ssm.pojo.*;
import com.giao.ssm.service.*;
import com.giao.ssm.util.UtilFuns;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jnlp.ExtendedService;
import java.util.*;

/**
 * 报运
 */
@Controller
@RequestMapping("/exp")
public class ExportController {
    @Autowired
    ContractCService contractCService;
    @Autowired
    ContractProductCService Pro;

    @Autowired
    ExtCproductCService ext;
    @Autowired
    ExportCService exportCService;
    @Autowired
    OutProductService outProductService;
    @Autowired
    ExportProductCService exportProductCService;

    @Autowired
    ExtEproductCService extEproductCService;

    //报运
    @RequestMapping("/addExp")
    public String addExp(String[] contractId, Map map) {
                /*
        添加ExportC
         */
        String contractNos = "";
        int o = 0;
        for (int i = 0; i < contractId.length; i++) {
            ContractC contract = outProductService.view(contractId[i]);
            //以空格作为分隔符
            contractNos += contract.getContractNo() + " ";
        }
        //工具类，删除最后一个字符
        contractNos = UtilFuns.delLastChar(contractNos);

        ExportC export = new ExportC();
        export.setExportId(UUID.randomUUID().toString());

        //工具类，拼串
        export.setContractIds(UtilFuns.joinStr(contractId, ","));
        export.setCustomerContract(contractNos);
        //0草稿
        export.setState((short) 0);

        exportCService.addExp(export);
        //添加ExportC完成

        //处理货物信息
        for (int i = 0; i < contractId.length; i++) {
            ContractC contract = outProductService.view(contractId[i]);
            for (ContractProductC cp : contract.getContractProductCS()) {
                ExportProductC ep = new ExportProductC();
                ep.setExportProductId(UUID.randomUUID().toString());
                ep.setExportId(export.getExportId());                    //绑定外键
                //数据搬家，将合同下的对应的货物信息写入到报运下的货物信息中
                ep.setFactoryId(cp.getFactoryC().getFactoryId());
                ep.setProductName(cp.getFactoryC().getFactoryName());
                ep.setProductNo(cp.getProductNo());
                ep.setPackingUnit(cp.getPackingUnit());
                ep.setCnumber(cp.getCnumber());
                ep.setBoxNum(cp.getBoxNum());
                ep.setPrice(cp.getPrice());
                exportProductCService.addExpPr(ep);
                //添加ExportProductC完成


                List<ExtCproductC> extCproducts = cp.getExtCproductCS();
                //处理附件信息

                for (ExtCproductC extcp : extCproducts) {
                    o++;
                    ExtEproductC extep = new ExtEproductC();

                    //copyProperties spring
                    BeanUtils.copyProperties(extcp, extep);        //spring工具类，数据的拷贝

                    extep.setExtEproductId(UUID.randomUUID().toString());
                    extep.setExportProductId(ep.getExportProductId());        //绑定外键

                    extep.setFactoryId(extcp.getFactoryC().getFactoryId());
                    extep.setProductName(extcp.getFactoryC().getFactoryName());

                    extEproductCService.addExEp(extep);
                }
            }
        }
        System.out.println(o);


        return "redirect:/exp/findExp";
    }

    public String uuid() {
        String s = UUID.randomUUID().toString();
        s = s.replace("-", "");
        return s;
    }

    //查询
    @RequestMapping("/findExp")
    public String findExp(@RequestParam(value = "pn",defaultValue = "1")Integer pn,Map map) {
        PageHelper.startPage(pn,3);
        List<ExportC> dataList = exportCService.findExpAll();
        PageInfo pages=new PageInfo(dataList,5);
        map.put("dataList", pages);
        return "basicinfo/export/jExportList";
    }


    //删除
    @RequestMapping("/deleteExp")
    public String deleteExp(String[] exportId) {
        exportCService.deleteExp(exportId);
        return "redirect:/exp/findExp";
    }


//    @RequestMapping("/updateExp")
//    public String updateExp(ExportC exportC) {
//        System.out.println(exportC);
//        exportCService.updateExp(exportC);
//        return "redirect:/exp/findExp";
//
//    }

    @RequestMapping("/updateExp")
    public String update(ExportC export,
                         String[] mr_exportProductId,
                         Integer[] mr_changed,
                         Integer[] mr_orderNo,
                         Integer[] mr_cnumber,
                         Double[] mr_grossWeight,
                         Double[] mr_netWeight,
                         Double[] mr_sizeLength,
                         Double[] mr_sizeWidth,
                         Double[] mr_sizeHeight,
                         Double[] mr_exPrice,
                         Double[] mr_tax
//                         @RequestParam("mr_exportProductId") String[] mr_exportProductId,
//                         @RequestParam("mr_changed")Integer[] mr_changed,
//                         @RequestParam("mr_orderNo")Integer[] mr_orderNo,
//                         @RequestParam("mr_cnumber")Integer[] mr_cnumber,
//                         @RequestParam("mr_grossWeight")Double[] mr_grossWeight,
//                         @RequestParam("mr_netWeight")Double[] mr_netWeight,
//                         @RequestParam("mr_sizeLength")Double[] mr_sizeLength,
//                         @RequestParam("mr_sizeWidth")Double[] mr_sizeWidth,
//                         @RequestParam("mr_sizeHeight")Double[] mr_sizeHeight,
//                         @RequestParam("mr_exPrice")Double[] mr_exPrice,
//                         @RequestParam("mr_tax")Double[] mr_tax
    ) {
        System.out.println("mr_exportProductId"+ Arrays.toString(mr_exportProductId));
        System.out.println(Arrays.toString(mr_changed));
        System.out.println(mr_cnumber);


        exportCService.update(export,
                mr_exportProductId,
                mr_orderNo,
                mr_cnumber,
                mr_grossWeight,
                mr_netWeight,
                mr_sizeLength,
                mr_sizeWidth,
                mr_sizeHeight,
                mr_exPrice,
                mr_tax,
                mr_changed
        );

        return "redirect:/exp/findExp";
    }


    //查一个
//    @RequestMapping("/findOne")
//    public String findOne(Map map, String exportId) {
//        ExportC c = exportCService.selectOne(exportId);
//        map.put("obj", c);
//        return "basicinfo/export/jExportUpdate";
//    }
    //批量修改
    @RequestMapping("/findOne")
    public String toupdate(String exportId, Model model) {
        ExportC c = exportCService.selectOne(exportId);
        model.addAttribute("obj", c);

        //准备批量修改控件的数据mrecord
        model.addAttribute("mRecordData", exportCService.getMrecordData(exportId));

        return "/basicinfo/export/jExportUpdate";
    }


    @RequestMapping("/sb")
    public String sb(String []exportId){
        exportCService.sb(exportId);
        return "redirect:/exp/findExp";
    }
    @RequestMapping("/qx")
    public String qx(String []exportId){
        exportCService.qx(exportId);
        return "redirect:/exp/findExp";
    }
}

