package com.giao.ssm.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractProductC {
    private String contractProductId;

    private ContractC contractC;
    private List<ExtCproductC> extCproductCS;
    private FactoryC factoryC;

    private ExtCproductC extCproductC;


    private String contractId;

    private String factoryId;

    private String productName;

    private String productNo;

    private String productImage;

    private String productDesc;

    private String loadingRate;

    private String packingUnit;

    private Integer cnumber;

    private Integer outNumber;

    private Integer finished;

    private Double grossWeight;

    private Double netWeight;

    private Double sizeLenght;

    private Double sizeWidth;

    private Double sizeHeight;

    private String productRequest;

    private String factory;

    private Double price;

    private Double amount;

    private String cunit;

    private Integer boxNum;

    private Double exPrice;

    private String exUnit;

    private Double noTax;

    private Double tax;

    private Double costPrice;

    private Double costTax;

    private Integer accessories;

    private Integer orderNo;




   
}