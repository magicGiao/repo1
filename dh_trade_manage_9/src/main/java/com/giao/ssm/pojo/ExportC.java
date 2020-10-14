package com.giao.ssm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExportC {
    private String exportId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date inputDate;

    private String epnum;
    private String extnum;


    private String contractIds;

    private String customerContract;

    private String lcno;

    private String consignee;

    private String marks;

    private String shipmentPort;

    private String destinationPort;

    private String transportMode;

    private String priceCondition;

    private String remark;

    private Short boxNum;

    private Short cnumber;

    private String packingUnit;

    private BigDecimal grossWeight;

    private BigDecimal netWeight;

    private BigDecimal sizeLenght;

    private BigDecimal sizeWidth;

    private BigDecimal sizeHeight;

    private BigDecimal csize;

    private BigDecimal amount;

    private BigDecimal noTax;

    private BigDecimal tax;

    private BigDecimal costPrice;

    private BigDecimal costTax;

    private Short state;

    private String createBy;

    private String createDept;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;


}