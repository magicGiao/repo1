package com.giao.ssm.pojo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class OutProductVO {

    private String contractId;
    private String customName;
    private String contractNo;
    private String productNo;
    private String cnumber;
    private String factoryName;
    private String deliveryPeriod;
    private String shipTime;
    private String tradeTerms;
}
