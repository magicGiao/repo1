package com.giao.ssm.mapper;

import com.giao.ssm.pojo.ExportC;

import java.util.List;

public interface ExportCMapper {
    int insertSelective(ExportC exportC);

    List<ExportC> findExpAll();

    int deleteExp(String[] exportId);

    ExportC selectOne(String exportId);

    int updateExp(ExportC exportC);


    int sb(String[] exportId);
    int qx(String[] exportId);
}
