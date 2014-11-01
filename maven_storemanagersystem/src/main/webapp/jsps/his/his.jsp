<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://www.itcast.cn/paginnationBar" prefix="itcast"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>历史记录</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/maple.css'/>"></link>
<style type="text/css">
.tx td {
	padding: 3px;
}

.store {
	width: 100%;
	border: 1px solid gray;
	border-collapse: collapse;
}

.store td {
	border: 1px solid gray;
	padding: 3px;
}

.store a {
	text-decoration: underline;
	color: blue;
}
</style>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<script type="text/javascript">
	$(function() {
		$.get("${pageContext.request.contextPath}/store_findAllStore.action",
				function(data) {
					$(data).each(
							function() {
								$("#storeselect").append(
										"<option value='"+this.id+"'>"
												+ this.name + "</option>");
							});
				});
	});
</script>
</head>
<body>
	<table border="0" class="tx" width="100%">
		<tr>
			<td>当前位置&gt;&gt;首页&gt;&gt;出入库记录</td>
		</tr>
	</table>
	<br>
	<table border="0" width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td rowspan="1"><s:form method="get" name="select"
					action="history_paginationList" namespace="/">

					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="tx" align="center">
						<colgroup>
							<col width="20%" align="right">
							<col width="*%" align="left">
						</colgroup>
						<tr>
							<td bgcolor="a0c0c0" style="padding-left:10px;" colspan="2"
								align="left"><b>查询条件：</b></td>
						</tr>
						<tr>
							<td>简记码：</td>
							<td><s:textfield cssClass="tx" type="text" name="goods.nm"></s:textfield>
							</td>
						</tr>
						<tr>
							<td>货物名称：</td>
							<td><s:textfield cssClass="tx" type="text" name="goods.name"></s:textfield>
							</td>
						</tr>
						<tr>
							<td>选择仓库：</td>
							<td><select class="tx" style="width: 120px;"
								id="storeselect" name="goods.store.id">
									<option value="null">--请选择仓库--</option>
							</select></td>
						</tr>
						<tr>
							<td colspan="2" align="right" style="padding-top:10px;"><input
								class="tx" style="width:120px;margin-right:30px;" type="button"
								onclick="document.forms[0].submit();" value="查询"></td>
						</tr>
					</table>
				</s:form></td>
			<td valign="top" width="20%">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td background="<c:url value='/picture/loginpage.gif'/>"
							align="center"><br> <font color="red">操作步骤</font></td>
					</tr>
					<tr>
						<td background="<c:url value='/picture/bg1.jpg'/>"
							style="padding-left:10px;">1.显示某种货物的出入库记录 <br />
							2.根据条件查询某种货的库存情况
						</td>
					</tr>
					<tr>
						<td><img src="<c:url value='/picture/bottom.jpg'/>"></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr valign="top">
			<td rowspan="2">
				<form action="" method="post" name="select">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="tx" align="center">
						<colgroup>
							<col width="20%" align="right">
							<col width="*%" align="left">
						</colgroup>
						<tr>
							<td bgcolor="a0c0c0" style="padding-left:10px;" colspan="1"
								align="left"><b>货物库存：</b></td>
						</tr>
						<tr>
							<td>
								<table class="store">
									<tr style="background:#D2E9FF;text-align: center;">
										<td>简记码</td>
										<td>名称</td>
										<td>时间</td>
										<td>类型</td>
										<td>单位</td>
										<td>数量</td>
										<td>库存余量</td>
										<td>仓库</td>
										<td>操作员</td>
									</tr>
									<%-- 							<s:iterator value="goodsList" var="goods">
										<s:iterator value="#goods.histories" var="history">
											<tr align="center">
												<td>
												<s:property value="#goods.nm"/>
												</td>
												<td><s:property value="#goods.name"/></td>
												<td>
												<s:property value="datetime"/>
												</td>
												<td>
												<s:if test="operatetype==1">
												入库
												</s:if>
												<s:if test="operatetype==2">
												出库
												</s:if>
												</td>
												<td>
												<s:property value="#goods.unit"/>
												</td>
												<td>
												<s:property value="amount"/>
												</td>
												<td>
												<s:property value="remain"/>
												</td>
												<td>
												<s:property value="#goods.store.name"/>
												</td>
												<td>
												<s:property value="operateruser"/>
												</td>
											</tr>
										</s:iterator>
									</s:iterator> --%>

									<s:iterator value="paginationBean.list" var="history">
										<tr align="center">
											<td><s:property value="goods.nm" /></td>
											<td><s:property value="goods.name" /></td>
											<td><s:property value="datetime" /></td>
											<td><s:if test="operatetype==1">
												入库
												</s:if> <s:if test="operatetype==2">
												出库
												</s:if></td>
											<td><s:property value="goods.unit" /></td>
											<td><s:property value="amount" /></td>
											<td><s:property value="remain" /></td>
											<td><s:property value="goods.store.name" /></td>
											<td><s:property value="operateruser" /></td>
										</tr>
									</s:iterator>
								</table>
							</td>
						</tr>
					</table>
					<%-- <div align="right">
						<s:if test="paginationBean.page>1">
							<a
								href="${pageContext.request.contextPath }/history_paginationList.action?page=1&${paginationBean.paramsUrl}">首页</a>
							<a
								href="${pageContext.request.contextPath }/history_paginationList.action?page=${paginationBean.page-1 }&${paginationBean.paramsUrl}">上一页</a>
						</s:if>

						<!--定义起始页码-->
						<s:set value="paginationBean.page-5" var="begin" scope="page"></s:set>
						<s:if test="#attr.begin<1">
							<s:set value="1" var="begin" scope="page"></s:set>
						</s:if>

						<!--在起始页码确定时定义结束页码-->
						<s:set value="#attr.begin+9" var="end" scope="page"></s:set>

						<!--在判断结束页码有没有超限-->
						<s:if test="#attr.end>paginationBean.totalPage">
							<s:set value="paginationBean.totalPage" var="end" scope="page"></s:set>
						</s:if>
						<!--根据end定义begin-->
						<s:set value="#attr.end-9" var="begin" scope="page"></s:set>

						<s:if test="#attr.begin<1">
							<s:set value="1" var="begin" scope="page"></s:set>
						</s:if>

						<!--生成分页条-->
						<s:iterator begin="#attr.begin" end="#attr.end" var="i">
							<a
								href="${pageContext.request.contextPath }/history_paginationList.action?page=${i}&${paginationBean.paramsUrl}">
								<s:if test="#i==paginationBean.page">
									<font color="blue">[${i}]</font>
								</s:if> <s:else>
								[${i}]
								</s:else>
							</a>
						</s:iterator>

						<s:if test="paginationBean.page<paginationBean.totalPage">

							<a
								href="${pageContext.request.contextPath }/history_paginationList.action?page=${paginationBean.page+1}&${paginationBean.paramsUrl}">下一页</a>
							<a
								href="${pageContext.request.contextPath }/history_paginationList.action?page=${paginationBean.totalPage}&${paginationBean.paramsUrl}">尾页</a>

						</s:if>
						<input type="text" size="2" name="page" /> <input type="button"
							value="go" size="2" />
					</div> --%>
					<itcast:paginationBar paramsUrl="${paginationBean.paramsUrl }"
						totalPage="${paginationBean.totalPage }"
						requestUrl="/history_paginationList.action"
						page="${paginationBean.page }"></itcast:paginationBar>
				</form>
			</td>
		</tr>
	</table>
</body>
</html>

