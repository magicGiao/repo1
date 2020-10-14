package com.giao.ssm.service;

import com.giao.ssm.pojo.ContractC;
import com.giao.ssm.pojo.OutProductVO;

import java.util.List;

public interface OutProductService {

    List<OutProductVO> find(String inputDate);

    ContractC view(String contractId);
}
