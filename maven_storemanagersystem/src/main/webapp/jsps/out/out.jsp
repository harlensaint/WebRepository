<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>出库</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/maple.css'/>"></link>
<style type="text/css">
.tx td {
	padding: 3px;
}
</style>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/easyui/jquery-ui-1.10.4.custom.js"></script>
<style type="text/css">
@IMPORT
	url("${pageContext.request.contextPath}/easyui/jquery-ui-1.10.4.custom.css")
	;
</style>
<script type="text/javascript">
	$(function() {
		$("input[name='nm']").blur(function() {
			//alert("ddd");
			var $nm = $(this).val();
			$.post("${pageContext.request.contextPath}/goods_findBynmOnly", {
				nm : $nm
			}, function(data) {

				if (data == null) {
					$("input[name='id']").val("");
					$("input[name='name']").val("");
					$("input[name='unit']").val("");
					$("input[name='amount']").val("");
					$("input[name='store.id']").val("");
				} else {
					$("input[name='id']").val(data.id);
					$("input[name='name']").val(data.name);
					$("input[name='unit']").val(data.unit);
					$("input[name='amount']").val(data.amount);
					$("input[name='store.id']").val(data.store.name);
				}
			});
		});
	});
</script>

</head>
<body>
	<table border="0" class="tx" width="100%">
		<tr>
			<td>当前位置&gt;&gt;首页&gt;&gt;出库</td>
		</tr>
	</table>
	<br>
	<table border="0" width="100%" cellpadding="0" cellspacing="0">
		<tr valign="top">
			<td rowspan="2"><s:form action="goods_out" method="post"
					namespace="/" name="select">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="tx" align="center">
						<colgroup>
							<col width="20%" align="right">
							<col width="*%" align="left">
						</colgroup>
						<tr>
							<td bgcolor="a0c0c0" style="padding-left:10px;" colspan="2"
								align="left"><b>货物出库登记：</b></td>
						</tr>
						<tr>
							<td>简记码：</td>
							<td><s:hidden cssClass="tx" type="hidden" name="id"></s:hidden>

								<s:textfield cssClass="tx" type="text" name="nm"></s:textfield>
							</td>
						</tr>
						<tr>
							<td>货物名称：</td>
							<td><s:textfield cssClass="tx" type="text" name="name"></s:textfield>
							</td>
						</tr>
						<tr>
							<td>计量单位：</td>
							<td><s:textfield cssClass="tx" type="text" name="unit"
									readonly="true" disabled="true"></s:textfield></td>
						</tr>
						<tr>
							<td>入库数量：</td>
							<td><s:textfield cssClass="tx" type="text" name="amount"></s:textfield>
								<s:actionerror /></td>
						</tr>
						<tr>
							<td>选择仓库：</td>
							<td><s:textfield type="text" readonly="true" cssClass="tx"
									disabled="true" name="store.id"></s:textfield></td>
						</tr>
						<tr>
							<td colspan="2" align="center" style="padding-top:10px;"><input
								class="tx" style="width:120px;margin-right:30px;"
								onclick="document.forms[0].submit();" type="button" value="出库">
								<input class="tx" style="width:120px;margin-right:30px;"
								type="button" value="取消" onclick="window.location.href='${pageContext.request.contextPath}/goods_list.action'"></td>
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
							style="padding-left:10px;">1.输入简记码从数据库<br />查询是否已经存在此 <br />货物
							<br /> 2.没有则直接输入货物名称<br />模糊查询是否存在此货物 <br> 3.如果存在则自动加载所有信息
							<br> 4.只要求用户输入出库数量 <br /> 5.记录入库历史记录 <br /> 6.减少库存，不可以为负数
						</td>
					</tr>
					<tr>
						<td><img src="<c:url value='/picture/bottom.jpg'/>"></td>
					</tr>
				</table> <!-- 操作步骤 -->
			</td>
		</tr>
	</table>
</body>
</html>

