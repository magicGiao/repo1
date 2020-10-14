package com.giao.ssm.service.impl;

import com.giao.ssm.mapper.ExtCproductCMapper;
import com.giao.ssm.pojo.ExtCproductC;
import com.giao.ssm.service.ExtCproductCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExtCproductCServiceImpl implements ExtCproductCService {
@Autowired
    ExtCproductCMapper extCproductCMapper;

    @Override
    public int addExt(ExtCproductC extCproductC) {
        return extCproductCMapper.addExt(extCproductC);
    }

    @Override
    public List<ExtCproductC> findExtAll(String contractProductId) {
        return extCproductCMapper.findExtAll(contractProductId);
    }

    @Override
    public int deleteExt(String extCproductId) {
        return extCproductCMapper.deleteExt(extCproductId);
    }

    @Override
    public int deleteExtAll(String[] contractProductId) {
        return extCproductCMapper.deleteExtAll(contractProductId);
    }

    @Override
    public int updateExt(ExtCproductC extCproductC) {
        return extCproductCMapper.updateExt(extCproductC);
    }

    @Override
    public ExtCproductC findExtOne(String extCproductId) {
        return extCproductCMapper.findExtOne(extCproductId);
    }

    @Override
    public List<ExtCproductC> findExtZj(String contractProductId) {
        return extCproductCMapper.findExtZj(contractProductId);
    }


}
