package com.giao.ssm.mapper;


import com.giao.ssm.pojo.ContractC;

import java.util.List;

public interface ContractCMapper {
   List<ContractC> findConAll();

   ContractC findConOne(String contractId);

   int addCon(ContractC contractC);

   int deleteCon(String[] contractId);

   int updateConGui(String contractId);
   int updateConCao(String contractId);
   int updateConDai(String contractId);


   int updateCon(ContractC contractC);
}