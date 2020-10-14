package com.giao.ssm.service;

import com.giao.ssm.pojo.ContractProductC;

import java.util.List;

public interface ContractProductCService {
    int addPro(ContractProductC contractProductC);
    List<ContractProductC> findProAll(String contractId);
    int deletePro(String[] contractProductId);

    ContractProductC findProOne(String contractProductId);
    int updatePro(ContractProductC contractProductC);
  List  <ContractProductC> findProZj(String contractId);
//    ContractProductC findProZj(String contractId);

    List <ContractProductC> findProOneDelete(String contractId);
}
