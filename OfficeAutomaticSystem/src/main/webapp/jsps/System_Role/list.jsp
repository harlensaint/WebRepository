<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>岗位列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script language="javascript"
	src="${pageContext.request.contextPath}/jsps/script/jquery.js"></script>
<script language="javascript"
	src="${pageContext.request.contextPath}/jsps/script/pageCommon.js"
	charset="utf-8"></script>
<script language="javascript"
	src="${pageContext.request.contextPath}/jsps/script/PageUtils.js"
	charset="utf-8"></script>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/jsps/style/blue/pageCommon.css" />
<script type="text/javascript">
	
</script>
</head>
<body>

	<div id="Title_bar">
		<div id="Title_bar_Head">
			<div id="Title_Head"></div>
			<div id="Title">
				<!--页面标题-->
				<img border="0" width="13" height="13"
					src="${pageContext.request.contextPath}/jsps//style/images/title_arrow.gif" />
				岗位管理
			</div>
			<div id="Title_End"></div>
		</div>
	</div>

	<div id="MainArea">
	<s:actionerror/>
		<table cellspacing="0" cellpadding="0" class="TableStyle">

			<!-- 表头-->
			<thead>
				<tr align="CENTER" valign="MIDDLE" id="TableTitle">
					<td width="200px">岗位名称</td>
					<td width="300px">岗位说明</td>
					<td>相关操作</td>
				</tr>
			</thead>

			<!--显示数据列表-->
			<tbody id="TableData" class="dataContainer" datakey="roleList">
				<s:iterator value="list" var="role">
					<tr class="TableDetail1 template">
						<td>${role.name}&nbsp;</td>
						<td>${role.description}&nbsp;</td>
						<td>
						<s:if test="#session.loginUser.checkPrivileges('岗位删除')">
						<a onClick="return window.confirm('确定要删除此岗位信息吗？')" href="${pageContext.request.contextPath}/role_delete.action?id=${role.id}">删除</a>
						</s:if>
						<s:if test="#session.loginUser.checkPrivileges('岗位修改')">
							<a href="${pageContext.request.contextPath}/role_updateUI.action?id=${role.id}">修改</a> 
							</s:if>
							<s:if test="#session.loginUser.checkPrivileges('设置权限')">
							<a href="${pageContext.request.contextPath}/role_setPrivilegeUI.action?id=${id}">设置权限</a>
							</s:if>
							</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>

		<!-- 其他功能超链接 -->
		<div id="TableTail">
			<div id="TableTail_inside">
				<a
					href="${pageContext.request.contextPath }/jsps/System_Role/saveUI.jsp">
							
					<img
					src="${pageContext.request.contextPath}/jsps/style/images/createNew.png" />
				</a>
			</div>
		</div>
	</div>
</body>
</html>
