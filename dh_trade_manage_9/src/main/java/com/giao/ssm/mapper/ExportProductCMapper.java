package com.giao.ssm.mapper;

import com.giao.ssm.pojo.ExportProductC;

import java.util.List;
import java.util.Map;


public interface ExportProductCMapper {
   int insertSelective(ExportProductC exportProductC);

//   List<ExportProductC>find(String exportId);

   List<ExportProductC> find(Map paraMap);

   ExportProductC get(String exportProductId);

   int updateEpp(ExportProductC exportProductC);
}