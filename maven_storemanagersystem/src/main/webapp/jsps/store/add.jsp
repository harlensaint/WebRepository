<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>添加新仓库</title>
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
	src="${pageContext.request.contextPath}/jqueryvalidation/jquery.metadata.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jqueryvalidation/jquery.validate.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jqueryvalidation/messages_zh.js"></script>
<script type="text/javascript">
	$(function() {

		$("#store_add").validate({
			rules : {
				name : {
					required : true,
					rangelength : [ 3, 30 ]
				},
				addr : {
					required : true,
					rangelength : [ 3, 30 ]
				},

				manager : {

					required : true,
					rangelength : [ 3, 10 ]
				}

			},
			messages : {
				name : {
					required : "必须填写",
					rangelength : "长度3-30个汉字"
				},
				addr : {
					required : "必须填写",
					rangelength : "长度3-30个汉字"
				},

				manager : {

					required : "必须填写",
					rangelength : "长度3-10个汉字"
				}
			}
		});

		//阻止冒泡事件的发生
		$("#store_add").submit(function(event) {
			event.stopPropagation();
		});
	});
</script>

</head>
<body>
	<!-- 中间内容（开始） -->
	<table border="0" class="tx" width="100%">
		<tr>
			<td>当前位置&gt;&gt;首页&gt;&gt;创建新仓库</td>
		</tr>
	</table>
	<br>
	<table border="0" width="100%" cellpadding="0" cellspacing="0">
		<tr valign="top">
			<td rowspan="2"><s:form action="store_add" method="post"
					name="select" namespace="/">

					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="tx" align="center">
						<colgroup>
							<col width="20%" align="right">
							<col width="*%" align="left">
						</colgroup>
						<tr>
							<td bgcolor="a0c0c0" style="padding-left:10px;" colspan="2"
								align="left"><b>创建新仓库：</b></td>
						</tr>
						<tr>
							<td>仓库名称：</td>
							<td><s:textfield cssClass="tx" type="text" name="name"></s:textfield></td>
						</tr>
						<tr>
							<td>仓库地址：</td>
							<td><s:textfield cssClass="tx" type="text" name="addr"></s:textfield></td>
						</tr>
						<tr>
							<td>库管员：</td>
							<td><s:textfield cssClass="tx" type="text" name="manager"></s:textfield></td>
						</tr>
						<tr>
							<td colspan="2" align="center" style="padding-top:10px;"><input
								class="tx" style="width:120px;margin-right:30px;" type="submit"
								value="确定">

								<button class="tx" style="width:120px;margin-right:30px;"
									type="reset"
									onclick="window.location.href='${pageContext.request.contextPath}/store_list.action'">取消</button>

							</td>
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
							style="padding-left:10px;">1.输入仓库的名称,地址 <br />
							2.保存后直接去显示仓库列表
						</td>
					</tr>
					<tr>
						<td><img src="<c:url value='/picture/bottom.jpg'/>"></td>
					</tr>
				</table>
		</tr>
	</table>
</body>
</html>

