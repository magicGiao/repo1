package com.giao.ssm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtCproductC {
    private String extCproductId;

    private ContractProductC contractProductC;
    private FactoryC factoryC;

    private String factoryId;

    private String contractProductId;

    private Short ctype;

    private String productName;

    private String productNo;

    private String productImage;

    private String productDesc;

    private String loadingRate;

    private String packingUnit;

    private Short cnumber;

    private Short outNumber;

    private Short finished;

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

    private Short boxNum;

    private Double exPrice;

    private String exUnit;

    private Double noTax;

    private Double tax;

    private Double costPrice;

    private Double costTax;

    private Short accessories;

    private Short orderNo;



}