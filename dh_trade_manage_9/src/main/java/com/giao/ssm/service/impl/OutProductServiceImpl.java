package com.giao.ssm.service.impl;

import com.giao.ssm.mapper.OutProductMapper;
import com.giao.ssm.pojo.ContractC;
import com.giao.ssm.pojo.OutProductVO;
import com.giao.ssm.service.OutProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OutProductServiceImpl implements OutProductService {

    @Autowired
    OutProductMapper outProductMapper;

    @Override
    public List<OutProductVO> find(String inputDate) {
        return outProductMapper.find(inputDate);
    }

    @Override
    public ContractC view(String contractId) {
        return outProductMapper.view(contractId);
    }


}
