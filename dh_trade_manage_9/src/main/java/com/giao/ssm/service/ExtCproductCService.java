package com.giao.ssm.service;

import com.giao.ssm.pojo.ExtCproductC;

import java.util.List;

public interface ExtCproductCService {
    int addExt(ExtCproductC extCproductC);

    List<ExtCproductC> findExtAll(String contractProductId);

    int deleteExt(String extCproductId);
    int deleteExtAll(String[] contractProductId);

    int updateExt(ExtCproductC extCproductC);

    ExtCproductC findExtOne(String extCproductId);

    List<ExtCproductC> findExtZj(String contractProductId);
}
