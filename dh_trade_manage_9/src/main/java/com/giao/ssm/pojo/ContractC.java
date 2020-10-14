package com.giao.ssm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractC {

    private String contractId;
    List<ContractProductC> contractProductCS;
    private  int cpnum;
    private  int extnum;

    private ContractProductC contractProductC;
    private FactoryC factoryC;

    private String offeror;

    private String contractNo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date signingDate;

    private String inputBy;

    private String checkBy;

    private String inspector;

    private Double totalAmount;

    private String request;

    private String customName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date shipTime;

    private Integer importNum;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deliveryPeriod;

    private String remark;

    private Integer printStyle;

    private Integer oldState;

    private Integer state;

    private Integer outState;

    private String createBy;

    private String createDept;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    private String tradeTerms;

}