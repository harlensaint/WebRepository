<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>货物统计</title>
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
			<td>当前位置&gt;&gt;首页&gt;&gt;货物库存</td>
		</tr>
	</table>
	<br>
	<table border="0" width="100%" cellpadding="0" cellspacing="0"
		align="center">
		<tr>
			<td rowspan="1"><s:form action="goods_list" method="post"
					name="select">
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
							<td><s:textfield cssClass="tx" type="text" name="nm"></s:textfield>

							</td>
						</tr>
						<tr>
							<td>货物名称：</td>
							<td><s:textfield cssClass="tx" type="text" name="name"></s:textfield>
							</td>
						</tr>
						<tr>
							<td>选择仓库：</td>
							<td><s:select cssClass="tx" cssStyle="width: 120px;"
									id="storeselect" name="store.id" 
									 list="{'--请选择仓库--'}">
								</s:select></td>
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
							style="padding-left:10px;">1.显示所有货物的库存情况 <br />
							2.根据条件查询某种货的库存情况 <br /> 3.出入库完成后显示某种货物的库存情况
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
										<td>计量单位</td>
										<td>库存数量</td>
										<td>所在仓库</td>
										<td>操作</td>
									</tr>
									<s:iterator value="goodsList" var="goods">
										<tr align="center">
											<td>${nm}</td>
											<td>${name }</td>
											<td>${unit}</td>
											<td>${amount }</td>
											<td>${store.name }</td>
											<td><a href="<c:url value='/jsps/save/save.jsp'/>">入库</a>
												<a href="<c:url value='/jsps/out/out.jsp'/>">出库</a> <s:a
													action="goods_historyList" namespace="/">历史记录</s:a></td>
										</tr>
									</s:iterator>
								</table>
							</td>
						</tr>
					</table>
					<div align="right">
						<a href="#">首页</a> <a href="#">上一页</a> <a href="#">[1]</a> <a
							href="#">[2]</a> <a href="#">[3]</a> <a href="#">[4]</a> <a
							href="#">[5]</a> <a href="#">[6]</a> <a href="#">[7]</a> <a
							href="#">[8]</a> <a href="#">[9]</a> <a href="#">下一页</a> <a
							href="#">尾页</a> <input type="text" size="2" name="page" /> <input
							type="button" value="go" size="2" />
					</div>
				</form>
			</td>
		</tr>
	</table>
</body>
</html>

