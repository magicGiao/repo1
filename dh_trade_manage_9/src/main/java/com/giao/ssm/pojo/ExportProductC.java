package com.giao.ssm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExportProductC {
    private String exportProductId;

    private String contractProductId;

    private String exportId;

    private String factoryId;

    private String contractId;

    private String contractNo;

    private String productName;

    private String productNo;

    private String productImage;

    private String productDesc;

    private String loadingRate;

    private String packingUnit;

    private Integer cnumber;

    private Short outNumber;

    private Short finished;

    private Double grossWeight;

    private Double netWeight;

    private BigDecimal sizeLenght;

    private Double sizeWidth;

    private Double sizeHeight;

    private String productRequest;

    private String factory;

    private Double price;

    private BigDecimal amount;

    private String cunit;

    private Integer boxNum;

    private Double exPrice;

    private String exUnit;

    private BigDecimal noTax;

    private Double tax;

    private BigDecimal costPrice;

    private BigDecimal costTax;

    private Short accessories;

    private Integer orderNo;


}