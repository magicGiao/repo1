package com.giao.ssm.mapper;

import com.giao.ssm.pojo.FactoryC;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Resource;
import java.util.List;


public interface FactoryCMapper {
    List<FactoryC> findFactAll();
    int addFact(FactoryC factoryC);

    int deleteByid(String []factoryIds);

    int stop(String[] factoryId);
    int start(String[] factoryId);
    FactoryC findFactOne(String factoryId);
    int updateFac(FactoryC factoryC);

}
