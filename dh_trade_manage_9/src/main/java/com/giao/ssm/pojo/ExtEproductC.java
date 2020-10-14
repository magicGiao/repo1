package com.giao.ssm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtEproductC {
    private String extEproductId;

    private String factoryId;

    private String ctype;

    private String productName;

    private String productNo;

    private String productImage;

    private String productDesc;

    private String loadingRate;

    private String packingUnit;

    private Short cnumber;

    private Short outNumber;

    private Short finished;

    private BigDecimal grossWeight;

    private BigDecimal netWeight;

    private BigDecimal sizeLenght;

    private BigDecimal sizeWidth;

    private BigDecimal sizeHeight;

    private String productRequest;

    private String factory;

    private BigDecimal price;

    private BigDecimal amount;

    private String cunit;

    private Short boxNum;

    private BigDecimal exPrice;

    private String exUnit;

    private BigDecimal noTax;

    private BigDecimal tax;

    private BigDecimal costPrice;

    private BigDecimal costTax;

    private Short accessories;

    private Short orderNo;

    private String exportProductId;

    private String contractProductId;


}