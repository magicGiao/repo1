package com.giao.ssm.mapper;

import com.giao.ssm.pojo.ContractProductC;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ContractProductCMapper {
    int addPro(ContractProductC contractProductC);

    List<ContractProductC> findProAll(String contractId);

    int deletePro(String[] contractProductId);

    ContractProductC findProOne(String contractProductId);
    List<ContractProductC> findProZj(String contractId);
//    ContractProductC findProZj(String contractId);

    List <ContractProductC> findProOneDelete(String contractId);

    int updatePro(ContractProductC contractProductC);
}
