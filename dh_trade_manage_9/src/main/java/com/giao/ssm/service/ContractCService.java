package com.giao.ssm.service;

import com.giao.ssm.pojo.ContractC;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ContractCService {
    List<ContractC> findConAll();


    PageInfo pageAndFindCon(int page,int pageSize);

    ContractC findConOne(String contractId);

    int addCon(ContractC contractC);

    int deleteCon(String[] contractId);


    int updateConGui(String contractId);
    int updateConCao(String contractId);
    int updateConDai(String contractId);


    int updateCon(ContractC contractC);

}
