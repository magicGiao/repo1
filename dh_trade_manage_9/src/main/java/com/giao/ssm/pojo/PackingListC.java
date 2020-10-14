package com.giao.ssm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackingListC {
    private String id;

    private String seller;

    private String buyer;

    private String invoiceNo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date invoiceDate;

    private String marks;

    private String descriptions;

    private String exportIds;

    private String exportNos;

    private Short state;

    private String createBy;

    private String createDept;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;


}