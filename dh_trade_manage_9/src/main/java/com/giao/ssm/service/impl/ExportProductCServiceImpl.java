package com.giao.ssm.service.impl;

import com.giao.ssm.mapper.ExportProductCMapper;
import com.giao.ssm.pojo.ExportProductC;
import com.giao.ssm.service.ExportProductCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service

public class ExportProductCServiceImpl implements ExportProductCService {
   @Autowired
   ExportProductCMapper exportProductCMapper;

   @Override
   public int addExpPr(ExportProductC exportProductC) {
      return exportProductCMapper.insertSelective(exportProductC);
   }

   @Override
   public List<ExportProductC> find(Map paraMap) {
      return exportProductCMapper.find(paraMap);
   }

   @Override
   public ExportProductC get(String exportProductId) {
      return exportProductCMapper.get(exportProductId);
   }

   @Override
   public int updateEpp(ExportProductC exportProductC) {
      return exportProductCMapper.updateEpp(exportProductC);
   }

//   @Override
//   public List<ExportProductC> find(String exportId) {
//      return exportProductCMapper.find(exportId);
//   }
}