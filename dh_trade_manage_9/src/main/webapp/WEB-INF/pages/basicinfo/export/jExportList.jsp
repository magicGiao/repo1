<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<link href="${ctx}/static/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">

</head>
<script href="${ctx}/static/bootstrap-3.3.7-dist/js/bootstrap.js"></script>

<body>
<form name="icform" method="post">

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="view"><a href="#" onclick="formSubmit('toview.action','_self');this.blur();">查看</a></li>
<li id="update"><a href="#" onclick="formSubmit('${ctx}/exp/findOne','_self');this.blur();">修改</a></li>
<li id="delete"><a href="#" onclick="formSubmit('${ctx}/exp/deleteExp','_self');this.blur();">删除</a></li>
<li id="new"><a href="#" onclick="formSubmit('${ctx}/pa/insertTo','_self');this.blur();">装箱</a></li>
<li id="new"><a href="#" onclick="formSubmit('${ctx}/exp/sb','_self');this.blur();">上报</a></li>
<li id="new"><a href="#" onclick="formSubmit('${ctx}/exp/qx','_self');this.blur();">取消</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
<div class="textbox" id="centerTextbox">
  <div class="textbox-header">
  <div class="textbox-inner-header">
  <div class="textbox-title">
    出口报运列表
  </div> 
  </div>
  </div>
  
<div>

<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<thead>
	<tr>
		<td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('exportId',this)"></td>
		<td class="tableHeader">序号</td>
		<td class="tableHeader">合同或确认书号</td>
		<td class="tableHeader">信用证号</td>
		<td class="tableHeader">货物数/附件数</td>
		<td class="tableHeader">收货人及地址</td>
		<td class="tableHeader">装运港</td>
		<td class="tableHeader">目的港</td>
		<td class="tableHeader">运输方式</td>
		<td class="tableHeader">价格条件</td>
		<td class="tableHeader">制单日期</td>
		<td class="tableHeader">状态</td>
	</tr>
	</thead>
	<tbody class="tableBody" >
	
	<c:forEach items="${dataList.list}" var="o" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
		<td><input type="checkbox" name="exportId" value="${o.exportId}"/></td>
		<td>${status.index+1}</td>
		<td><a href="toview.action?id=${o.exportId}">${o.customerContract}</a></td>
		<td>${o.lcno}</td>
		<td align="center">${o.epnum}/${o.extnum}</td>
		<td>${o.consignee}</td>
		<td>${o.shipmentPort}</td>
		<td>${o.destinationPort}</td>
		<td>${o.transportMode}</td>
		<td>${o.priceCondition}</td>
		<td><fmt:formatDate value="${o.inputDate}" pattern="yyyy-MM-dd"/></td>
		<td>
			<c:if test="${o.state==1}"><font color="green">已上报</font></c:if>
			<c:if test="${o.state==0}">草稿</c:if>
		</td>
	</tr>
	</c:forEach>
	
	</tbody>
</table>
</div>
 
</div>
</div>
 
</form>
<!-- 显示分页信息 -->
<div class="row">
	<!--分页文字信息  -->

	<div class="col-md-3" id="page_info">--当前 ${dataList.pageNum }页,总${dataList.pages }
		页,总 ${dataList.total }条记录
	</div>
	<!-- 分页条信息 -->
	<div class="col-md-9" id="page_nave">
		<nav aria-label="Page navigation">
			<ul class="pagination">
				<li><a href="${pageContext.request.contextPath}/exp/findExp?pn=1">首页</a></li>
				<c:if test="${dataList.hasPreviousPage }">
					<li><a href="${pageContext.request.contextPath}/exp/findExp?pn=${dataList.pageNum-1}"
						   aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
					</a></li>
				</c:if>

				<%--&lt;%&ndash;循环遍历出连续要显示的页数&ndash;%&gt;--%>
				<c:forEach items="${dataList.navigatepageNums }" var="page_Num">
					<%-- &lt;%&ndash;page_Num是不是当前页码，如果是，高亮显示&ndash;%&gt;--%>
					<c:if test="${page_Num == dataList.pageNum }">
						<li class="active"><a href="#">${page_Num }</a></li>
					</c:if>
					<%--&lt;%&ndash;如果不是当前页码，就不显示高亮了&ndash;%&gt;--%>
					<c:if test="${page_Num != dataList.pageNum }">
						<li><a href="${pageContext.request.contextPath}/exp/findExp?pn=${page_Num }">${page_Num }</a>
						</li>
					</c:if>

				</c:forEach>
				<c:if test="${dataList.hasNextPage }">
					<li><a href="${pageContext.request.contextPath}/exp/findExp?pn=${dataList.pageNum+1 }"
						   aria-label="Next"> <span aria-hidden="true">&raquo;</span>
					</a></li>
				</c:if>
				<li><a href="${pageContext.request.contextPath}/exp/findExp?pn=${dataList.pages}">末页</a></li>
			</ul>
		</nav>
	</div>
</div>
</body>
</html>

