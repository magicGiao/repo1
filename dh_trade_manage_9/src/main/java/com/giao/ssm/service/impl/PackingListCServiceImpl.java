package com.giao.ssm.service.impl;

import com.giao.ssm.mapper.PackingListCMapper;
import com.giao.ssm.pojo.ExportC;
import com.giao.ssm.pojo.PackingListC;
import com.giao.ssm.service.ExportCService;
import com.giao.ssm.service.PackingListCService;
import com.giao.ssm.util.UtilFuns;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class PackingListCServiceImpl implements PackingListCService {
    @Autowired
    PackingListCMapper packingListCMapper;

    @Autowired
    ExportCService exportCService;

    @Override
    public List<PackingListC> find(Map paraMap) {
        return packingListCMapper.find(paraMap);
    }


    //拆串，拼串
    private PackingListC spellString(PackingListC packingList){
        String _exportIds = "";
        String _exportNos = "";

        String[] _s = packingList.getExportIds().split(",");		//id|no
        for(int i=0;i<_s.length;i++){
            String[] _tmp = _s[i].split("\\|");						//正则表达式，转义
            _exportIds +=  _tmp[0] + "|";
            _exportNos +=  _tmp[1] + "|";
        }
        _exportIds = UtilFuns.delLastChar(_exportIds);
        _exportNos = UtilFuns.delLastChar(_exportNos);

        packingList.setExportIds(_exportIds);
        packingList.setExportNos(_exportNos);

        return packingList;
    }

    //拼接HTML片段
    public String getDivDataCreate(String[] exportIds){
        StringBuffer sBuf = new StringBuffer();
        for(int i=0;i<exportIds.length;i++){
            ExportC export = exportCService.selectOne(exportIds[i]);
            sBuf.append("<input type=\"checkbox\" name=\"exportIds\" checked value=\"").append(exportIds[i]).append("|").append(export.getCustomerContract()).append("\" class=\"input\"/>");
            sBuf.append(export.getCustomerContract()).append("&nbsp;&nbsp;");
        }

        return sBuf.toString();
    }

    public int insert(PackingListC packingList) {
        this.spellString(packingList);

        packingList.setId(UUID.randomUUID().toString());
        packingList.setState((short) 0);					//0草稿1已上报
//        packingList.setCreateBy("默认");
//        packingList.setCreateDept("默认");
//        Date date = new Date();
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        System.out.println(formatter.format(date));

//        packingList.setCreateTime(date);
//        packingListCMapper.insert(packingList);

        return packingListCMapper.insert(packingList);
    }

    @Override
    public PackingListC get(String id) {
        return packingListCMapper.get(id);
    }

    //拼接HTML片段(修改)
    public String getDivDataUpdate(String[] exportIds, String[] exportNos){
        StringBuffer sBuf = new StringBuffer();
        for(int i=0;i<exportIds.length;i++){
            sBuf.append("<input type=\"checkbox\" name=\"exportIds\" checked value=\"").append(exportIds[i]).append("|").append(exportNos[i]).append("\" class=\"input\"/>");
            sBuf.append(exportNos[i]).append("&nbsp;&nbsp;");
        }

        return sBuf.toString();
    }

    public int update(PackingListC packingList) {
        this.spellString(packingList);
        return packingListCMapper.update(packingList);
    }

    //拼接HMTL片段(查看)
    public String getDivDataView(String[] exportNos){
        StringBuffer sBuf = new StringBuffer();
        for(int i=0;i<exportNos.length;i++){
            sBuf.append(exportNos[i]).append("&nbsp;&nbsp;");
        }

        return sBuf.toString();
    }

    @Override
    public int deletePa(String[] id) {
        return packingListCMapper.deletePa(id);
    }

    @Override
    public int sb(String[] id) {
        return packingListCMapper.sb(id);
    }

    @Override
    public int qx(String[] id) {
        return packingListCMapper.qx(id);
    }
}
