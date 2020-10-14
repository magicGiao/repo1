<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../../baselist.jsp" %>
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
                        <li id="view"><a href="#"
                                         onclick="formSubmit('${pageContext.request.contextPath}/fac/findFactOne','_self');this.blur();">查看</a>
                        </li>
                        <li id="new"><a href="#"
                                        onclick="formSubmit('${pageContext.request.contextPath}/fac/addFacTo','_self');this.blur();">新增</a>
                        </li>
                        <li id="update"><a href="#"
                                           onclick="formSubmit('${pageContext.request.contextPath}/fac/updateto','_self');this.blur();">修改</a>
                        </li>
                        <li id="delete"><a href="#"
                                           onclick="formSubmit('${pageContext.request.contextPath}/fac/deleteByid','_self');this.blur();">删除</a>
                        </li>
                        <li id="delete"><a href="#"
                                           onclick="formSubmit('${pageContext.request.contextPath}/fac/deleteByid','_self');this.blur();">删除N</a>
                        </li>
                        <li id="new"><a href="#"
                                        onclick="formSubmit('${pageContext.request.contextPath}/fac/start','_self');this.blur();">启用</a>
                        </li>
                        <li id="new"><a href="#"
                                        onclick="formSubmit('${pageContext.request.contextPath}/fac/stop','_self');this.blur();">停用</a>
                        </li>
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

            <div class="eXtremeTable">
                <table id="ec_table" class="tableRegion" width="98%">
                    <thead>
                    <tr>
                        <td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
                        <td class="tableHeader">序号</td>
                        <td class="tableHeader">厂家全称</td>
                        <td class="tableHeader">缩写</td>
                        <td class="tableHeader">联系人</td>
                        <td class="tableHeader">电话</td>
                        <td class="tableHeader">手机</td>
                        <td class="tableHeader">传真</td>
                        <td class="tableHeader">验货员</td>
                        <td class="tableHeader">状态</td>
                    </tr>
                    </thead>
                    <tbody class="tableBody">

                    <c:forEach items="${dataList.list}" var="o" varStatus="status">
                        <tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'">
                            <td><input type="checkbox" name="factoryId" value="${o.factoryId}"/></td>
                            <td>${status.index+1}</td>
                            <td><a href="${ctx}/fac/findFactOne?factoryId=${o.factoryId}">${o.fullName}</a></td>
                            <td><a href="${ctx}/fac/findFactOne?factoryId=${o.factoryId}">${o.factoryName}</a></td>
                            <td>${o.contactor}</td>
                            <td>${o.phone}</td>
                            <td>${o.mobile}</td>
                            <td>${o.fax}</td>
                            <td>${o.inspector}</td>
                            <td>
                                <c:if test="${o.state==1}"><a href="${ctx}/fac/stop?factoryId=${o.factoryId}"><font
                                        color="green">启用</font></a></c:if>
                                <c:if test="${o.state==0}"><a
                                        href="${ctx}/fac/start?factoryId=${o.factoryId}">停用</a></c:if>
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
                <li><a href="${pageContext.request.contextPath}/fac/findFacAll?pn=1">首页</a></li>
                <c:if test="${dataList.hasPreviousPage }">
                    <li><a href="${pageContext.request.contextPath}/fac/findFacAll?pn=${dataList.pageNum-1}"
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
                        <li><a href="${pageContext.request.contextPath}/fac/findFacAll?pn=${page_Num }">${page_Num }</a>
                        </li>
                    </c:if>

                </c:forEach>
                <c:if test="${dataList.hasNextPage }">
                    <li><a href="${pageContext.request.contextPath}/fac/findFacAll?pn=${dataList.pageNum+1 }"
                           aria-label="Next"> <span aria-hidden="true">&raquo;</span>
                    </a></li>
                </c:if>
                <li><a href="${pageContext.request.contextPath}/fac/findFacAll?pn=${dataList.pages}">末页</a></li>
            </ul>
        </nav>
    </div>
</div>

</div>


</body>
</html>

