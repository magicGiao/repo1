package com.giao.ssm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FactoryC {
    private String factoryId;
    private String fullName;
    private String factoryName;
    private String contactor;
    private String phone;
    private String mobile;
    private String fax;
    private String cnote;
    private Integer ctype;
    private String state;
    private String inspector;
    private Integer orderNo;
    private String createBy;
    private String createDept;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

}
