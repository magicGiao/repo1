package com.giao.ssm.mapper;

import com.giao.ssm.pojo.ContractC;
import com.giao.ssm.pojo.OutProductVO;

import java.util.List;

public interface OutProductMapper {
    List<OutProductVO> find(String inputDate);

    ContractC view(String contractId);

}
