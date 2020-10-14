package com.giao.ssm.controller;

import com.giao.ssm.util.SqlDao;
import com.giao.ssm.util.file.FileUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.util.List;

@Controller
public class ChartController {

	@Resource
	private SqlDao sqlDao;
	
	//生产厂家销售饼形图
	/*
	 * 开发步骤：
	 * 1、组织数据源
	 * 2、拼接成xml
	 * 3、创建一个文件txt格式，xml 工具类
	 * 4、转向对应目录下的index.html
	 */
	@RequestMapping("/stat/chart/factorySale.action")
	public String factorySale(HttpServletRequest request, Model model) throws FileNotFoundException {
		//真实路径
		String path = request.getSession().getServletContext().getRealPath("/");	
		
		//1、组织数据源
		String sql = "SELECT f.factory_name,cp.countnum FROM (SELECT factory_id,factory_name FROM factory_c) f RIGHT JOIN (SELECT factory_id,COUNT(*) AS countnum FROM contract_product_c GROUP BY factory_id ) cp ON f.factory_id=cp.factory_id";
		//List<String>
		List<String> dataList=sqlDao.executeSQL(sql);
		System.out.println(dataList.size());
		//2、拼接成xml
		//拼接数据为一个xml字符串
		StringBuffer sBuf = new StringBuffer();
		sBuf.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sBuf.append("<pie>");
		for (int i = 0; i < dataList.size();) {
			// 在for循环内部控制当前记录标识
			sBuf.append("  <slice title=\"").append(dataList.get(i++)).append("\">").append(dataList.get(i++)).append("</slice>");
		}
		sBuf.append("</pie>");

		//3、创建一个文件txt格式，xml 工具类
		//输出文件，如果目录下没有文件，直接创建；如果目录下文件存在，覆盖。
		FileUtil fu = new FileUtil();
		fu.createTxt(path + "/stat/chart/factorysale", "data.xml", sBuf.toString(), "utf-8");
		model.addAttribute("forward","factorysale");
		return "/stat/chart/jStat";
	}

	//产品销售排行，畅销产品排行(柱状图)
	@RequestMapping("/stat/chart/productSale.action")
	public String productSale(HttpServletRequest request,Model model) throws FileNotFoundException {
		// 真实路径
		String path = request.getSession().getServletContext().getRealPath("/");

		//mysql
		//String sql = "SELECT product_no,SUM(cnumber) AS sumnum FROM contract_product_c GROUP BY product_no ORDER BY SUM(cnumber)  DESC LIMIT 15";

		//oracle
		String sql="select t.* from(select product_no,sum(cnumber) as sumnum from contract_product_c group by product_no order by sum(cnumber) desc) t where rownum<=15";
		List<String> dataList=sqlDao.executeSQL(sql);
		System.out.println(dataList.size());

		StringBuffer sBuf = new StringBuffer();
		sBuf.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sBuf.append("<chart>");
		sBuf.append("<series>");

		int xid = 0;			//对应标识
		for(int i=0;i<dataList.size();){
			sBuf.append("<value xid=\"").append(xid++).append("\">").append(dataList.get(i++)).append("</value>");
			i++;		//skip
		}
		sBuf.append("</series>");
		sBuf.append("<graphs>");
		sBuf.append("<graph gid=\"30\" color=\"#FFCC00\" gradient_fill_colors=\"#111111, #1A897C\">");

		xid = 0;//重新初始化
		for(int i=0;i<dataList.size();){
			i++;		//skip
			sBuf.append("<value xid=\"").append(xid++).append("\">").append(dataList.get(i++)).append("</value>");
		}

		sBuf.append("</graph>");
		sBuf.append("</graphs>");
		sBuf.append("</chart>");

		FileUtil fu = new FileUtil();
		fu.createTxt(path + "/stat/chart/productsale", "data.xml", sBuf.toString(), "utf-8");


		model.addAttribute("forward","productsale");

		return "/stat/chart/jStat";
	}
}