package com.giao.ssm.mapper;


import com.giao.ssm.pojo.PackingListC;

import java.util.List;
import java.util.Map;

public interface PackingListCMapper {
     List<PackingListC> find(Map paraMap);

     int insert(PackingListC packingListC);

     PackingListC get(String id);

     int update(PackingListC packingListC);

     int deletePa(String[] id);

     int sb(String[] id);
     int qx(String[] id);
}