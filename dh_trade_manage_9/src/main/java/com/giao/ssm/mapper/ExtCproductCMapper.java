package com.giao.ssm.mapper;

import com.giao.ssm.pojo.ExtCproductC;

import java.util.List;

public interface ExtCproductCMapper {

    int addExt(ExtCproductC extCproductC);

    List<ExtCproductC> findExtAll(String contractProductId);


    int deleteExt(String extCproductId);
    int deleteExtAll(String[] contractProductId);

    List<ExtCproductC> findExtZj(String contractProductId);

    int updateExt(ExtCproductC extCproductC);

    ExtCproductC findExtOne(String extCproductId);
}
