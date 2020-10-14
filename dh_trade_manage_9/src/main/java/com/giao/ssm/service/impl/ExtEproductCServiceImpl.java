package com.giao.ssm.service.impl;


import com.giao.ssm.mapper.ExtEproductCMapper;
import com.giao.ssm.pojo.ExtEproductC;
import com.giao.ssm.service.ExtEproductCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExtEproductCServiceImpl implements ExtEproductCService {
@Autowired
   ExtEproductCMapper extEproductCMapper;


   @Override
   public int addExEp(ExtEproductC extEproductC) {
      return extEproductCMapper.insertSelective(extEproductC);
   }
}