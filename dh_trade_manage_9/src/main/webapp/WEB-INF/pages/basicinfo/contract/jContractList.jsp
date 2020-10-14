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
<li id="view"><a href="#" onclick="formSubmit('${ctx}/con/findConOne','_self');this.blur();">查看</a></li>
<li id="new"><a href="#" onclick="formSubmit('${ctx}/con/addTo','_self');this.blur();">新增</a></li>
<li id="update"><a href="#" onclick="formSubmit('${ctx}/con/updateTo','_self');this.blur();">修改</a></li>
<li id="delete"><a href="#" onclick="formSubmit('${ctx}/con/deleteCon','_self');this.blur();">删除</a></li>
<li id="delete"><a href="#" onclick="formSubmit('${ctx}/con/updateConDai','_self');this.blur();">上报</a></li>
<li id="new"><a href="#" onclick="formSubmit('${ctx}/con/updateConCao','_self');this.blur();">取消</a></li>
<li id="new"><a href="#" onclick="formSubmit('${ctx}/con/dy','_self');this.blur();">打印</a></li>
<li id="new"><a href="#" onclick="formSubmit('${ctx}/con/dyMB','_self');this.blur();">模打印</a></li>
<li id="new"><a href="#" onclick="formSubmit('${ctx}/con/updateConGui','_self');this.blur();">归档</a></li>
</ul>
  </div>
</div>
</div>
</div>
<div class="textbox" id="centerTextbox">
  <div class="textbox-header">
  <div class="textbox-inner-header">
  <div class="textbox-title">
    生产厂家列表
  </div> 
  </div>
  </div>
  
<div>

<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<thead>
	<tr>
		<td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
		<td class="tableHeader">序号</td>
		<td class="tableHeader">客户名称</td>
		<td class="tableHeader">合同号</td>
		<td class="tableHeader">货物数/附件数</td>
		<td class="tableHeader">制单人</td>
		<td class="tableHeader">审单人</td>
		<td class="tableHeader">验货员</td>
		<td class="tableHeader">签单日期</td>
		<td class="tableHeader">交货日期</td>
		<td class="tableHeader">船期</td>
		<td class="tableHeader">总金额</td>
		<td class="tableHeader">状态</td>
		<td class="tableHeader">操作</td>
	</tr>
	</thead>
	<tbody class="tableBody" >
	
	<c:forEach items="${dataList.list}" var="o" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
		<td><input type="checkbox" name="contractId" value="${o.contractId}"/></td>
		<td>${status.index+1}</td>
		<td><a href="${ctx}/con/findConOne?contractId=${o.contractId}">${o.offeror}</a></td>
		<td>${o.contractNo}</td>
		<td>${o.cpnum}/${o.extnum}</td>
		<td>${o.inputBy}</td>
		<td>${o.checkBy}</td>
		<td>${o.inspector}</td>
		<td><fmt:formatDate value="${o.signingDate}" pattern="yyyy-MM-dd"/></td>
		<td><fmt:formatDate value="${o.deliveryPeriod}" pattern="yyyy-MM-dd"/></td>
		<td><fmt:formatDate value="${o.shipTime}" pattern="yyyy-MM-dd"/></td>
		<td>${o.totalAmount}</td>
		<td>
			<c:if test="${o.state==0}"><a href="${ctx}/con/updateConCao?contractId=${o.contractId}"><font color="green">已上报</font></a></c:if>
			<c:if test="${o.state==1}"><a href="${ctx}/con/updateConDai?contractId=${o.contractId}">草稿</a></c:if>
			<c:if test="${o.state==2}"><a href="${ctx}/con/updateConGui?contractId=${o.contractId}">待报运</a></c:if>
		</td>
		<td><a href="${ctx}/pro/addProTo?contractId=${o.contractId}">货物</a></td>
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
				<li><a href="${pageContext.request.contextPath}/con/findConAll?pn=1">首页</a></li>
				<c:if test="${dataList.hasPreviousPage }">
					<li><a href="${pageContext.request.contextPath}/con/findConAll?pn=${dataList.pageNum-1}"
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
						<li><a href="${pageContext.request.contextPath}/con/findConAll?pn=${page_Num }">${page_Num }</a>
						</li>
					</c:if>

				</c:forEach>
				<c:if test="${dataList.hasNextPage }">
					<li><a href="${pageContext.request.contextPath}/con/findConAll?pn=${dataList.pageNum+1 }"
						   aria-label="Next"> <span aria-hidden="true">&raquo;</span>
					</a></li>
				</c:if>
				<li><a href="${pageContext.request.contextPath}/con/findConAll?pn=${dataList.pages}">末页</a></li>
			</ul>
		</nav>
	</div>
</div>


</body>
</html>

