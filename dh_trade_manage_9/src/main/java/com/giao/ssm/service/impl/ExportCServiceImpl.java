package com.giao.ssm.service.impl;

import com.giao.ssm.mapper.ExportCMapper;
import com.giao.ssm.mapper.ExportProductCMapper;
import com.giao.ssm.pojo.ExportC;
import com.giao.ssm.pojo.ExportProductC;
import com.giao.ssm.service.ExportCService;
import com.giao.ssm.service.ExportProductCService;
import com.giao.ssm.util.UtilFuns;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExportCServiceImpl implements ExportCService {
    @Autowired
    ExportCMapper exportCMapper;
    @Autowired
    ExportProductCMapper exportProductCMapper;
    @Override
    public int addExp(ExportC exportC) {
        return exportCMapper.insertSelective(exportC);
    }

    @Override
    public List<ExportC> findExpAll() {
        return exportCMapper.findExpAll();
    }

    @Override
    public PageInfo<ExportC> pageAndfindExpAll(int page, int pageSize) {
        PageHelper.startPage(page,pageSize);
        PageInfo<ExportC> exportCPageInfo=new PageInfo<>(findExpAll());
        return exportCPageInfo;
    }

    @Override
    public int deleteExp(String[] exportId) {
        return exportCMapper.deleteExp(exportId);
    }

    @Override
    public ExportC selectOne(String exportId) {
        return exportCMapper.selectOne(exportId);
    }

    @Override
    public int updateExp(ExportC exportC) {
        return exportCMapper.updateExp(exportC);
    }


    public String getMrecordData(String exportId) {
        Map paraMap = new HashMap();
        paraMap.put("exportId", exportId);

        List<ExportProductC> oList = exportProductCMapper.find(paraMap);

        StringBuffer sBuf = new StringBuffer();
        for (ExportProductC ep : oList) {
            sBuf.append("addTRRecord(\"mRecordTable\", \"").append(ep.getExportProductId()).append("\", \"")
                    .append(ep.getProductNo()).append("\", \"").append(ep.getCnumber()).append("\", \"")
                    .append(UtilFuns.convertNull(ep.getGrossWeight())).append("\", \"")
                    .append(UtilFuns.convertNull(ep.getNetWeight())).append("\", \"")
//                    .append(UtilFuns.convertNull(ep.getSizeLength())).append("\", \"")
                    .append(UtilFuns.convertNull(ep.getSizeWidth())).append("\", \"")
                    .append(UtilFuns.convertNull(ep.getSizeHeight())).append("\", \"")
                    .append(UtilFuns.convertNull(ep.getExPrice())).append("\", \"")
                    .append(UtilFuns.convertNull(ep.getTax())).append("\");");
        }

        return sBuf.toString();

    }

    @Override
    public void update(ExportC export, String[] mr_exportProductId, Integer[] mr_orderNo, Integer[] mr_cnumber, Double[] mr_grossWeight, Double[] mr_netWeight, Double[] mr_sizeLength, Double[] mr_sizeWidth, Double[] mr_sizeHeight, Double[] mr_exPrice, Double[] mr_tax, Integer[] mr_changed) {
        exportCMapper.updateExp(export);
        //批量修改货物信息

        for(int i=0;i<mr_exportProductId.length;i++){
            //拿不到mr_changed值
//            if(mr_changed[i]!=null && mr_changed[i]==1){			//修改标识，只有用户修改的行才进行更新
                ExportProductC ep = exportProductCMapper.get(mr_exportProductId[i]);

                ep.setOrderNo(mr_orderNo[i]);
                ep.setCnumber(mr_cnumber[i]);
                ep.setGrossWeight(mr_grossWeight[i]);
                ep.setNetWeight(mr_netWeight[i]);
//                ep.setSizeLength(mr_sizeLength[i]);
                ep.setSizeWidth(mr_sizeWidth[i]);
                ep.setSizeHeight(mr_sizeHeight[i]);
                ep.setExPrice(mr_exPrice[i]);
                ep.setTax(mr_tax[i]);

                exportProductCMapper.updateEpp(ep);
//            }
        }
    }

    @Override
    public int sb(String[] exportId) {
        return exportCMapper.sb(exportId);
    }

    @Override
    public int qx(String[] exportId) {
        return exportCMapper.qx(exportId);
    }
}
