import com.giao.ssm.pojo.*;
import com.giao.ssm.service.*;
import com.github.pagehelper.PageInfo;
import lombok.SneakyThrows;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestPa {
    @Autowired
    ContractCService contractCService;
    @Autowired
    ContractProductCService Pro;

    @Autowired
    ExtCproductCService ext;

    @Autowired
    FactoryCService factoryCService;

    @Autowired
    OutProductService outProductService;

    @Autowired
    PackingListCService packingListCService;

    @Autowired
    UsertService usertService;

    @Test
    public void findAnd01(){
        List<FactoryC> factAll = factoryCService.findFactAll();
        for (FactoryC a:factAll){
            System.out.println(a);
        }
    }

    @Test
    public void findAndPage(){
        PageInfo pageInfo = factoryCService.pageAndfindFactAll(1,1);
        System.out.println(pageInfo);
        List<FactoryC> list = pageInfo.getList();
        for (FactoryC f:list){
            System.out.println(f);
        }
    }

    @Test
    public void findAndConPage(){
        PageInfo pageInfo=contractCService.pageAndFindCon(1,1);
        List<ContractC> list=pageInfo.getList();
        for (ContractC c:list){
            System.out.println(c);
        }


    }

    @Test
    public void find(){
        Map paraMap = null;
        List<PackingListC> pa=packingListCService.find(paraMap);
        System.out.println(pa);
//        System.out.println("2");

    }


    @SneakyThrows
    @Test
    public void pio(){


//        public String fMB(String inputDate){
            String inputDate="2020-12";
            System.out.println(inputDate);
          List<OutProductVO>ou=  outProductService.find(inputDate);
        System.out.println(ou);
            /*
             * POI实现excel打印
             * 1、大标题，合并单元格
             * 2、标题，修饰
             * 3、内容，修饰
             *
             */

//            Workbook wb = new HSSFWorkbook();		//创建一个工作簿
//            Sheet sheet = wb.createSheet();			//创建一个工作表
//            Row nRow = null;
//            Cell nCell = null;
//            int rowNo = 0;							//行号
//            int colNo = 1;							//列号：从第2列开始
//
//            //处理大标题sheet.addMergedRegion(new CellRangeAddress(开始行，结束行，开始列，结束列));
//            //合并单元格
//            sheet.addMergedRegion(new CellRangeAddress(0, 0, 1, 8));
//            //合并单元格的内容是写在合并前第一个单元格
//            nRow = sheet.createRow(rowNo++);
//
//            nCell = nRow.createCell(1);
//
//
//            nCell.setCellValue(inputDate.replaceFirst("-0", "-").replaceFirst("-", "年") + "月份出货表");		//yyyy-MM
//
//            //处理标题. 标题数组
//            String[] title = new String[]{"客户","订单号","货号","数量","工厂","工厂交期","船期","贸易条款"};
//            nRow = sheet.createRow(rowNo++);
//
//            for(int i=0;i<title.length;i++){
//                nCell = nRow.createCell(i+1);
//                nCell.setCellValue(title[i]);
//            }
//
//            //处理内容
//            List<OutProductVO> dataList=outProductService.find(inputDate);
//            for(int j=0;j<dataList.size();j++){
//                colNo = 1;				//初始化
//                OutProductVO op = dataList.get(j);
//
//                nRow = sheet.createRow(rowNo++);
//                //nRow.setHeightInPoints(24);
//
//                nCell = nRow.createCell(colNo++);
//                nCell.setCellValue(op.getCustomName());
//
//                nCell = nRow.createCell(colNo++);
//                nCell.setCellValue(op.getContractNo());
//
//                nCell = nRow.createCell(colNo++);
//                nCell.setCellValue(op.getProductNo());
//
//                nCell = nRow.createCell(colNo++);
//                nCell.setCellValue(op.getCnumber());
//
//                nCell = nRow.createCell(colNo++);
//                nCell.setCellValue(op.getFactoryName());
//
//                nCell = nRow.createCell(colNo++);
//                nCell.setCellValue(op.getDeliveryPeriod());
//
//                nCell = nRow.createCell(colNo++);
//                nCell.setCellValue(op.getShipTime());
//
//                nCell = nRow.createCell(colNo++);
//                nCell.setCellValue(op.getTradeTerms());
//
//            }
//
//            OutputStream os = new FileOutputStream("C:\\Users\\彭于晏\\Desktop\\outFMB.xls");
//            wb.write(os);
//
//            os.flush();
//            os.close();
        }


@Test
    public void login(){
//        usertService.login("admin","123456");
    ContractProductC c=new ContractProductC();
    c.setContractProductId("a787cca988b646d4804622d7ec0bed2d");
    c.setProductImage("222");
    Pro.updatePro(c);

}
    }

