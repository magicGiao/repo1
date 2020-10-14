package com.giao.ssm.service.impl;

import com.giao.ssm.mapper.ContractProductCMapper;
import com.giao.ssm.pojo.ContractProductC;
import com.giao.ssm.service.ContractProductCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractProductCServiceImpl implements ContractProductCService {
    @Autowired
    ContractProductCMapper contractProductCMapper;

    @Override
    public int addPro(ContractProductC contractProductC) {
        return contractProductCMapper.addPro(contractProductC);
    }

    @Override
    public List<ContractProductC> findProAll(String contractId) {
        return contractProductCMapper.findProAll(contractId);
    }

    @Override
    public int deletePro(String[] contractProductId) {
        return contractProductCMapper.deletePro(contractProductId);
    }



    @Override
    public ContractProductC findProOne(String contractProductId) {
        return contractProductCMapper.findProOne(contractProductId);
    }

    @Override
    public int updatePro(ContractProductC contractProductC) {
        return contractProductCMapper.updatePro(contractProductC);
    }

//    @Override
//    public ContractProductC findProZj(String contractId) {
//        return contractProductCMapper.findProZj(contractId);
//    }

    @Override
    public List<ContractProductC> findProZj(String contractId) {
        return contractProductCMapper.findProZj(contractId);
    }


    @Override
    public List<ContractProductC> findProOneDelete(String contractId) {
        return contractProductCMapper.findProOneDelete(contractId);
    }


}
