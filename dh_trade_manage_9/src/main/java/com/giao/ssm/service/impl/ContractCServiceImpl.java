package com.giao.ssm.service.impl;

import com.giao.ssm.mapper.ContractCMapper;
import com.giao.ssm.pojo.ContractC;
import com.giao.ssm.pojo.FactoryC;
import com.giao.ssm.service.ContractCService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ContractCServiceImpl implements ContractCService {

    @Autowired
    ContractCMapper contractCMapper;

    @Override
    public List<ContractC> findConAll() {
        return contractCMapper.findConAll();
    }


    @Override
    public PageInfo<ContractC> pageAndFindCon(int page, int pageSize) {
        PageHelper.startPage(page,pageSize);
        PageInfo<ContractC>conPageinfo = new PageInfo <> (findConAll());
        return conPageinfo;
    }

    @Override
    public ContractC findConOne(String contractId) {
        return contractCMapper.findConOne(contractId);
    }

    @Override
    public int addCon(ContractC contractC) {
        return contractCMapper.addCon(contractC);
    }

    @Override
    public int deleteCon(String[] contractId) {
        return contractCMapper.deleteCon(contractId);
    }

    @Override
    public int updateConGui(String contractId) {
        return contractCMapper.updateConGui(contractId);
    }

    @Override
    public int updateConCao(String contractId) {
        return contractCMapper.updateConCao(contractId);
    }

    @Override
    public int updateConDai(String contractId) {
        return contractCMapper.updateConDai(contractId);
    }

    @Override
    public int updateCon(ContractC contractC) {
        return contractCMapper.updateCon(contractC);
    }

}
