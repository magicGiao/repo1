package com.giao.ssm.mapper;

import com.giao.ssm.pojo.Usert;
import org.apache.ibatis.annotations.Param;


public interface UsertMapper {


    Usert login(@Param("uname") String uname,@Param("pwd") String pwd);

}