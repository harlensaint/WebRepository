<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>部门列表</title>
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
				部门管理
			</div>
			<div id="Title_End"></div>
		</div>
	</div>

	<div id="MainArea">
		${errMsg }
		<s:debug />
		<table cellspacing="0" cellpadding="0" class="TableStyle">

			<!-- 表头-->
			<thead>
				<tr align=center valign=middle id=TableTitle>
					<td width="150px">部门名称</td>
					<td width="150px">上级部门名称</td>
					<td width="200px">职能说明</td>
					<td>相关操作</td>
				</tr>
			</thead>

			<!--显示数据列表-->
			<tbody id="TableData" class="dataContainer" datakey="departmentList">
				<s:iterator value="recordList">
					<tr class="TableDetail1 template" align="center">
						<td><s:a action="department_list?parent.id=%{id}"
								namespace="/">${name}</s:a> &nbsp;</td>
						<td>${parent.name}&nbsp;</td>
						<td>${description}&nbsp;</td>
						<td><s:if test="#session.loginUser.checkPrivileges('部门删除')">
								<a onClick="return window.confirm('这将删除所有的下级部门，您确定要删除吗？')"
									href="${pageContext.request.contextPath }/department_delete?id=${id}&parent.id=${parent.id}">删除</a>
							</s:if> <s:if test="#session.loginUser.checkPrivileges('部门修改')">
								<s:a action="department_updateUI?id=%{id}" namespace="/">修改</s:a>
							</s:if></td>
					</tr>
				</s:iterator>
			</tbody>
		</table>

		<!-- 其他功能超链接 -->
		<div id="TableTail">
			<div id="TableTail_inside">
				<s:if test="#session.loginUser.checkPrivileges('部门添加')">
					<s:a action="department_saveUI" namespace="/">
						<img
							src="${pageContext.request.contextPath}/jsps/style/images/createNew.png" />
					</s:a>
				</s:if>
				<s:if test="%{parent.id!=null}">
					<s:a action="department_list?parent.id=%{parent.parent.id}"
						namespace="/">

						<IMG
							SRC="${pageContext.request.contextPath}/jsps/style/blue/images/button/ReturnToPrevLevel.png" />
					</s:a>
				</s:if>

			</div>
			<%@include file="/jsps/common/paginationBar.jsp"%>
			
			<s:form action="department_list"  namespace="/" method="post" id="paginationForm">
				<input name="parent.id" value="${id}" type="hidden"/>
			</s:form>
		</div>
	</div>

	<!--说明-->
	<div id="Description">
		说明：<br /> 1，列表页面只显示一层的（同级的）部门数据，默认显示最顶级的部门列表。<br />
		2，点击部门名称，可以查看此部门相应的下级部门列表。<br /> 3，删除部门时，同时删除此部门的所有下级部门。
	</div>

</body>
</html>
