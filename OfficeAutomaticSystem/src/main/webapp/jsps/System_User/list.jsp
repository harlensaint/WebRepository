<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
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
					src="${pageContext.request.contextPath}/jsps/style/images/title_arrow.gif" />
				用户管理
			</div>
			<div id="Title_End"></div>
		</div>
	</div>

	<div id="MainArea">
		<table cellspacing="0" cellpadding="0" class="TableStyle">

			<!-- 表头-->
			<thead>
				<tr align=center valign=middle id=TableTitle>
					<td width="100">登录名</td>
					<td width="100">姓名</td>
					<td width="100">所属部门</td>
					<td width="200">岗位</td>
					<td>备注</td>
					<td>相关操作</td>
				</tr>
			</thead>

			<!--显示数据列表-->
			<tbody id="TableData" class="dataContainer" datakey="userList">
				<s:iterator value="list">
					<tr class="TableDetail1 template">
						<td>${loginName}&nbsp;</td>
						<td>${name}&nbsp;</td>
						<td>${department.name}&nbsp;</td>
						<%-- <s:set value="" var="roles_text" scope="page"></s:set> --%>
						<%-- <s:iterator value="roles">
							<s:set value="name" var="roles_text"
								scope="page"></s:set>
						</s:iterator> --%>
						<td>
							<%-- <s:property value="#attr.roles_text"/> --%> <s:iterator
								value="roles">
								<s:property value="name" />
							</s:iterator> &nbsp;
						</td>
						<td>${description}&nbsp;</td>
						<td>
						
							<s:if test="#session.loginUser.checkPrivileges('用户删除')">
						<s:a onClick="return delConfirm()"
								action="user_delete?id=%{id}" namespace="/">删除</s:a>
								</s:if>
								
								<s:if test="#session.loginUser.checkPrivileges('用户修改')">
							<s:a action="user_updateUI?id=%{id}" namespace="/">修改</s:a>
							</s:if>
							
							<s:if test="#session.loginUser.checkPrivileges('用户初始化密码')">
							<a href="${pageContext.request.contextPath}/user_initPassword.action?id=${id}" onClick="return window.confirm('您确定要初始化密码为1234吗？')">初始化密码</a>
							</s:if>
							
							</td>
							
					</tr>
				</s:iterator>
			</tbody>
		</table>

		<!-- 其他功能超链接 -->
		<div id="TableTail">
			<div id="TableTail_inside">
			
				<s:if test="#session.loginUser.checkPrivileges('用户添加')">
					<a href="${pageContext.request.contextPath}/user_saveUI.action"><img
					src="${pageContext.request.contextPath}/jsps/style/images/createNew.png" /></a>
				</s:if>
					
			</div>
		</div>
	</div>

</body>
</html>
