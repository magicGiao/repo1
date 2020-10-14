package com.giao.ssm.service;

import com.giao.ssm.pojo.ExportC;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ExportCService {
    int addExp(ExportC exportC);
    List<ExportC> findExpAll();
    PageInfo pageAndfindExpAll(int page, int pageSize);
    int deleteExp (String[]exportId);
    ExportC selectOne(String exportId);

    int updateExp(ExportC exportC);

    String getMrecordData(String exportId);



    public void update(ExportC export,			//修改，用实体作为参数
                       String[] mr_exportId,
                       Integer[] mr_orderNo,
                       Integer[] mr_cnumber,
                       Double[] mr_grossWeight,
                       Double[] mr_netWeight,
                       Double[] mr_sizeLength,
                       Double[] mr_sizeWidth,
                       Double[] mr_sizeHeight,
                       Double[] mr_exPrice,
                       Double[] mr_tax,
                       Integer[] mr_changed
    );

    int sb(String[] exportId);
    int qx(String[] exportId);
}
