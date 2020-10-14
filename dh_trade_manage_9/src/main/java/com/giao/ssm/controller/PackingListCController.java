package com.giao.ssm.controller;

import com.giao.ssm.pojo.PackingListC;
import com.giao.ssm.service.PackingListCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/pa")
public class PackingListCController {

    @Autowired
    PackingListCService packingListCService;

    @RequestMapping("/find")
    public String find(Model model){
        List<PackingListC> dataList = packingListCService.find(null);
        model.addAttribute("dataList", dataList);
        return "basicinfo/packinglist/jPackingListList";
    }


    @RequestMapping("/insertTo")
    public String tocreate(String[] exportId, Model model){			//出口报运的id集合
        //携带出口报运的id集合	//显示装箱和报运的关系
        model.addAttribute("divData", packingListCService.getDivDataCreate(exportId));
        return "basicinfo/packinglist/jPackingListCreate";
    }


    @RequestMapping("/insert")
    public String insert(PackingListC packingList){
        System.out.println(packingList.getInvoiceDate());
        System.out.println(packingList);
        packingListCService.insert(packingList);

        return "redirect:/pa/find";
    }


    //去修改页面：
    @RequestMapping("/updateTo")
    public String toupdate(String id, Model model){
        PackingListC obj = packingListCService.get(id);
        model.addAttribute("obj", obj);

        String _s = packingListCService.getDivDataUpdate(obj.getExportIds().split("\\|"), obj.getExportNos().split("\\|"));
        model.addAttribute("divData", _s);

        return "/basicinfo/packinglist/jPackingListUpdate";
    }


    //真正修改
    @RequestMapping("/update")
    public String update(PackingListC packingList){
        packingListCService.update(packingList);

        return "redirect:/pa/find";
    }


    @RequestMapping("/view")
    public String toview(String id, Model model){
        PackingListC obj = packingListCService.get(id);
        model.addAttribute("obj", obj);

        model.addAttribute("divData", packingListCService.getDivDataView(obj.getExportNos().split("\\|")));

        return "/basicinfo/packinglist/jPackingListView";
    }


    @RequestMapping("/delete")
    public String delete(String []id){

        packingListCService.deletePa(id);
        return "redirect:/pa/find";
    }


    @RequestMapping("/sb")
    public String sb(String  []id){
        packingListCService.sb(id);
        return "redirect:/pa/find";
    }
    @RequestMapping("/qx")
    public String qx(String  []id){
        packingListCService.qx(id);
        return "redirect:/pa/find";
    }
}
