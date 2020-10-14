<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../../base.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../../baselist.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>

</head>
<body>
<form method="post">

    <div id="menubar">
        <div id="middleMenubar">
            <div id="innerMenubar">
                <div id="navMenubar">
                    <ul>
                        <li id="back"><a href="${ctx}/con/findConAll">返回</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <div class="textbox" id="centerTextbox">

        <div class="textbox-header">
            <div class="textbox-inner-header">
                <div class="textbox-title">
                    浏览购销合同信息
                </div>
            </div>
        </div>
        <div>
            <div>
                <table class="commonTable" cellspacing="1">
                    <tr>
                        <td class="columnTitle_mustbe">客户名称：</td>
                        <td class="tableContent">${obj.customName}</td>
                        <td class="columnTitle_mustbe">收购方：</td>
                        <td class="tableContent">${obj.offeror}</td>
                    </tr>
                    <tr>
                        <td class="columnTitle_mustbe">合同号：</td>
                        <td class="tableContent">${obj.contractNo}</td>
                        <td class="columnTitle_mustbe">打印版式：</td>
                        <td class="tableContentAuto">
                            <c:if test="${obj.printStyle=='2'}">两款</c:if>
                            <c:if test="${obj.printStyle=='1'}">一款</c:if>
                        </td>
                    </tr>
                    <tr>
                        <td class="columnTitle_mustbe">签单日期：</td>
                        <td class="tableContent"><fmt:formatDate value="${obj.signingDate}" pattern="yyyy-MM-dd"/></td>
                        <td class="columnTitle_mustbe">重要程度：</td>
                        <td class="tableContentAuto">
                            <c:if test="${obj.importNum==3}">★★★</c:if>
                            <c:if test="${obj.importNum==2}">★★</c:if>
                            <c:if test="${obj.importNum==1}">★</c:if>
                        </td>
                    </tr>
                    <tr>
                        <td class="columnTitle_mustbe">交货期限：</td>
                        <td class="tableContent"><fmt:formatDate value="${obj.deliveryPeriod}"
                                                                 pattern="yyyy-MM-dd"/></td>
                        <td class="columnTitle_mustbe">船期：</td>
                        <td class="tableContent"><fmt:formatDate value="${obj.shipTime}" pattern="yyyy-MM-dd"/></td>
                    </tr>
                    <tr>
                        <td class="columnTitle_mustbe">贸易条款：</td>
                        <td class="tableContent"></td>
                        <td class="columnTitle_mustbe">验货员：</td>
                        <td class="tableContent">${obj.inspector}</td>
                    </tr>
                    <tr>
                        <td class="columnTitle_mustbe">制单人：</td>
                        <td class="tableContent">${obj.inputBy}</td>
                        <td class="columnTitle_mustbe">审单人：</td>
                        <td class="tableContent">${obj.checkBy}</td>
                    </tr>
                    <tr>
                        <td class="columnTitle_mustbe">要求：</td>
                        <td class="tableContent">
                            <pre>${obj.request}</pre>
                        </td>
                        <td class="columnTitle_mustbe">说明：</td>
                        <td class="tableContent">
                            <pre>${obj.remark}</pre>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="textbox" id="centerTextbox">
            <div class="textbox-header">
                <div class="textbox-inner-header">
                    <div class="textbox-title">
                        货物列表
                    </div>
                </div>
            </div>

            <div>

                <div class="eXtremeTable">
                    <table id="ec_table" class="tableRegion" width="98%">
                        <thead>
                        <tr>
                            <td class="tableHeader">序号</td>
                            <td class="tableHeader">厂家名称</td>
                            <td class="tableHeader">货号</td>
                            <td class="tableHeader">数量</td>
                            <td class="tableHeader">包装单位</td>
                            <td class="tableHeader">装率</td>
                            <td class="tableHeader">箱数</td>
                            <td class="tableHeader">单价</td>
                            <td class="tableHeader">总金额</td>
                        </tr>
                        </thead>
                        <tbody class="tableBody">

                        <c:forEach items="${dataListC}" var="o" varStatus="status">
                            <tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'">
                                <td>${status.index+1}</td>
                                <td>${o.factoryC.factoryName}</td>
                                <td>${o.productNo}</td>
                                <td>${o.cnumber}</td>
                                <td>${o.packingUnit}</td>
                                <td>${o.loadingRate}</td>
                                <td>${o.boxNum}</td>
                                <td>${o.price}</td>
                                <td>${o.amount}</td>
                            </tr>


                            <c:set var="sum" value="0" />
                            <c:forEach items="${dataListE}" var="e" varStatus="s">

                                <c:if test="${e.contractProductId ne o.contractProductId}" >
<%--                                    <tr>--%>
<%--                                        <td>--%>
                                            <c:set var="sum" value="0" />
<%--                                        </td>--%>
<%--                                    </tr>--%>
                                </c:if>

                                <c:if test="${e.contractProductId==o.contractProductId}">
                                    <tr class="odd" onmouseover="this.className='highlight'"
                                        onmouseout="this.className='odd'">
                                        <td><font color="blue">附件：${sum = sum + 1}</font></td>
                                        <td>${e.factoryC.factoryName}</td>
                                        <td>${e.productNo}</td>
                                        <td>${e.cnumber}</td>
                                        <td>${e.packingUnit}</td>
                                        <td></td>
                                        <td></td>
                                        <td>${e.price}</td>
                                        <td>${e.amount}</td>

                                    </tr>

                                </c:if>
                            </c:forEach>

                        </c:forEach>

                        </tbody>
                    </table>
                </div>

            </div>

<%--            </c:if>--%>

</form>
</body>
</html>

