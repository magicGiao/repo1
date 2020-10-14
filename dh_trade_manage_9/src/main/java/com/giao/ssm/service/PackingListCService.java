package com.giao.ssm.service;


import com.giao.ssm.pojo.PackingListC;

import java.util.List;
import java.util.Map;

public interface PackingListCService {
     List<PackingListC> find(Map paraMap);

     //添加前
     String getDivDataCreate(String[] exportIds);

    int insert(PackingListC packingListC);

    PackingListC get(String id);

     String getDivDataUpdate(String[] exportIds, String[] exportNos);

    int update(PackingListC packingListC);

    //获取div在查看页面展示数据
    public String getDivDataView(String[] exportNos);

    int deletePa(String[] id);


    int sb(String[] id);
    int qx(String[] id);
}