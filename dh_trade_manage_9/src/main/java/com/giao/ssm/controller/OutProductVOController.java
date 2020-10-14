package com.giao.ssm.controller;


import com.giao.ssm.pojo.OutProductVO;
import com.giao.ssm.service.OutProductService;
import com.giao.ssm.util.DownloadUtil;
import lombok.SneakyThrows;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@Controller
@RequestMapping("/out")
public class OutProductVOController {

    @Autowired
    OutProductService outProductService;

    @RequestMapping("/outTo")
    public String outTo(){
        return "basicinfo/outproduct/jOutProduct";
    }

    @SneakyThrows
    @RequestMapping("/fMB")
    public String fMB(String inputDate){
        System.out.println(inputDate);
        /*
         * POI实现excel打印
         * 1、大标题，合并单元格
         * 2、标题，修饰
         * 3、内容，修饰
         *
         */

        Workbook wb = new HSSFWorkbook();		//创建一个工作簿
        Sheet sheet = wb.createSheet();			//创建一个工作表
        Row nRow = null;
        Cell nCell = null;
        int rowNo = 0;							//行号
        int colNo = 1;							//列号：从第2列开始

        //处理大标题sheet.addMergedRegion(new CellRangeAddress(开始行，结束行，开始列，结束列));
        //合并单元格
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 1, 8));
        //合并单元格的内容是写在合并前第一个单元格
        nRow = sheet.createRow(rowNo++);

        nCell = nRow.createCell(1);


        nCell.setCellValue(inputDate.replaceFirst("-0", "-").replaceFirst("-", "年") + "月份出货表");		//yyyy-MM

        //处理标题. 标题数组
        String[] title = new String[]{"客户","订单号","货号","数量","工厂","工厂交期","船期","贸易条款"};
        nRow = sheet.createRow(rowNo++);

        for(int i=0;i<title.length;i++){
            nCell = nRow.createCell(i+1);
            nCell.setCellValue(title[i]);
        }

        //处理内容
        List<OutProductVO> dataList=outProductService.find(inputDate);
        for(int j=0;j<dataList.size();j++){
            colNo = 1;				//初始化
            OutProductVO op = dataList.get(j);

            nRow = sheet.createRow(rowNo++);
            //nRow.setHeightInPoints(24);

            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(op.getCustomName());

            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(op.getContractNo());

            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(op.getProductNo());

            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(op.getCnumber());

            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(op.getFactoryName());

            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(op.getDeliveryPeriod());

            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(op.getShipTime());

            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(op.getTradeTerms());

        }

        OutputStream os = new FileOutputStream("C:\\Users\\彭于晏\\Desktop\\outFMB.xls");
        wb.write(os);

        os.flush();
        os.close();
        return outTo();
    }

    @SneakyThrows
    @RequestMapping("/MB")
    public String mb(String inputDate, HttpServletRequest request, HttpServletResponse response){
        System.out.println(inputDate);
        // linux下jdk1.8 方法获取时，不会拼接自己写的目录
        String path = request.getSession().getServletContext().getRealPath("/") + "/make/xlsprint/";
        InputStream is = new FileInputStream(new File(path + "tOUTPRODUCT.xls"));

        Workbook wb = new HSSFWorkbook(is); // 打开一个模板文件，工作簿
        Sheet sheet = wb.getSheetAt(0); // 获取到第一个工作表

        Row nRow = null;
        Cell nCell = null;
        int rowNo = 0; // 行号
        int colNo = 1; // 列号

        // 获取模板上的单元格样式
        nRow = sheet.getRow(2);

        // 客户的样式
        nCell = nRow.getCell(1);
        CellStyle customStyle = nCell.getCellStyle();

        // 订单号的样式
        nCell = nRow.getCell(2);
        CellStyle contractNoStyle = nCell.getCellStyle();

        // 货号的样式
        nCell = nRow.getCell(3);
        CellStyle productNoStyle = nCell.getCellStyle();

        // 数量的样式
        nCell = nRow.getCell(4);
        CellStyle numStyle = nCell.getCellStyle();

        // 生产厂家的样式
        nCell = nRow.getCell(5);
        CellStyle factoryStyle = nCell.getCellStyle();

        // 工厂交期的样式
        nCell = nRow.getCell(6);
        CellStyle dateStyle = nCell.getCellStyle();

        // 船期的样式和工厂交期的样式一样
        //nCell = nRow.getCell(7);
        //CellStyle shipStyle = nCell.getCellStyle();

        // 贸易条款的样式
        nCell = nRow.getCell(8);
        CellStyle tradeStyle = nCell.getCellStyle();

        // 处理大标题
        nRow = sheet.getRow(rowNo++); // 获取一个行对象
        nCell = nRow.getCell(colNo); // 获取一个单元格对象
        nCell.setCellValue(inputDate.replaceFirst("-0", "-").replaceFirst("-", "年") + "月份出货表"); // yyyy-MM

        rowNo++; // 跳过静态表格头

        // 处理内容
        List<OutProductVO> dataList = outProductService.find(inputDate);
        System.out.println(dataList);
        for (int j = 0; j < dataList.size(); j++) {
            colNo = 1; // 初始化
            OutProductVO op = dataList.get(j);

            nRow = sheet.createRow(rowNo++);
            nRow.setHeightInPoints(24);

            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(op.getCustomName());
            nCell.setCellStyle(customStyle);

            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(op.getContractNo());
            nCell.setCellStyle(contractNoStyle);

            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(op.getProductNo());
            nCell.setCellStyle(productNoStyle);

            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(op.getCnumber());
            nCell.setCellStyle(numStyle);

            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(op.getFactoryName());
            nCell.setCellStyle(factoryStyle);

            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(op.getDeliveryPeriod());
            nCell.setCellStyle(dateStyle);

            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(op.getShipTime());
            nCell.setCellStyle(dateStyle);

            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(op.getTradeTerms());
            nCell.setCellStyle(tradeStyle);
        }

        // OutputStream os = new FileOutputStream("d:\\outproduct.xls");
        // wb.write(os);
        //
        // os.flush();
        // os.close();

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        wb.write(os);

        DownloadUtil downloadUtil = new DownloadUtil(); // 直接弹出下载框，用户可以打开，可以保存
        downloadUtil.download(os, response, "出货表.xls");

        os.flush();
        os.close();
        return null;
    }

    @SneakyThrows
    @RequestMapping("/MB2")
    public String mb2(String inputDate,HttpServletRequest request, HttpServletResponse response){
        // linux下jdk1.8 方法获取时，不会拼接自己写的目录
        String path = request.getSession().getServletContext().getRealPath("/") + "/make/xlsprint/";
        InputStream is = new FileInputStream(new File(path + "tOUTPRODUCT.xlsx"));

        Workbook wb = new XSSFWorkbook(is); // 打开一个模板文件，工作簿 2007以上版本
        Sheet sheet = wb.getSheetAt(0); // 获取到第一个工作表

        Row nRow = null;
        Cell nCell = null;
        int rowNo = 0; // 行号
        int colNo = 1; // 列号

        // 获取模板上的单元格样式
        nRow = sheet.getRow(2);

        // 客户的样式
        nCell = nRow.getCell(1);
        CellStyle customStyle = nCell.getCellStyle();

        // 订单号的样式
        nCell = nRow.getCell(2);
        CellStyle contractNoStyle = nCell.getCellStyle();

        // 货号的样式
        nCell = nRow.getCell(3);
        CellStyle productNoStyle = nCell.getCellStyle();

        // 数量的样式
        nCell = nRow.getCell(4);
        CellStyle numStyle = nCell.getCellStyle();

        // 生产厂家的样式
        nCell = nRow.getCell(5);
        CellStyle factoryStyle = nCell.getCellStyle();

        // 日期的样式
        nCell = nRow.getCell(6);
        CellStyle dateStyle = nCell.getCellStyle();

        // 贸易条款的样式
        nCell = nRow.getCell(8);
        CellStyle tradeStyle = nCell.getCellStyle();

        // 处理大标题
        nRow = sheet.getRow(rowNo++); // 获取一个行对象
        nCell = nRow.getCell(colNo); // 获取一个单元格对象
        nCell.setCellValue(inputDate.replaceFirst("-0", "-").replaceFirst("-", "年") + "月份出货表"); // yyyy-MM

        rowNo++; // 跳过静态表格头

        // 处理内容
        List<OutProductVO> dataList = outProductService.find(inputDate);
        for (int j = 0; j < dataList.size(); j++) {
            colNo = 1; // 初始化
            OutProductVO op = dataList.get(j);

            nRow = sheet.createRow(rowNo++);
            nRow.setHeightInPoints(24);

            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(op.getCustomName());
            nCell.setCellStyle(customStyle);

            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(op.getContractNo());
            nCell.setCellStyle(contractNoStyle);

            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(op.getProductNo());
            nCell.setCellStyle(productNoStyle);

            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(op.getCnumber());
            nCell.setCellStyle(numStyle);

            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(op.getFactoryName());
            nCell.setCellStyle(factoryStyle);

            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(op.getDeliveryPeriod());
            nCell.setCellStyle(dateStyle);

            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(op.getShipTime());
            nCell.setCellStyle(dateStyle);

            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(op.getTradeTerms());
            nCell.setCellStyle(tradeStyle);
        }

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        wb.write(os);

        DownloadUtil downloadUtil = new DownloadUtil(); // 直接弹出下载框，用户可以打开，可以保存
        downloadUtil.download(os, response, "出货表.xlsx");

        os.flush();
        os.close();
        return null;
    }

}
