package com.giao.ssm.service.impl;

import com.giao.ssm.mapper.UsertMapper;
import com.giao.ssm.pojo.Usert;
import com.giao.ssm.service.UsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsertServiceImpl implements UsertService {
@Autowired
    UsertMapper usertMapper;

    @Override
    public Usert login(String uname, String pwd) {
        return usertMapper.login(uname,pwd);
    }
}
