<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<html>
<head>
<title>用户信息</title>
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
	$(function(){
		$("input[name='loginName']").blur(function(){
			var inputVal=$(this).val();
			
			if(inputVal!=""){
				$.post("${pageContext.request.contextPath}/user_findLoginName",{loginName:inputVal},function(data){
					if(data=="1"){
						//说明存在
						$("#Msg").html("<font color='red'>当前登陆名已经存在</font>");
						$("#save").attr("disabled","disabled");
					}else{
						//说明不存在
						$("#Msg").html("<font color='green'>当前登陆名可以使用</font>");
						$("#save").removeAttr("disabled");
					}					
				});
			}
		});		
	});
</script>
</head>
<body>

	<!-- 标题显示 -->
	<div id="Title_bar">
		<div id="Title_bar_Head">
			<div id="Title_Head"></div>
			<div id="Title">
				<!--页面标题-->
				<img border="0" width="13" height="13"
					src="${pageContext.request.contextPath}/jsps/style/images/title_arrow.gif" />
				用户信息
			</div>
			<div id="Title_End"></div>
		</div>
	</div>

	<!--显示表单内容-->
	<div id=MainArea>
		<s:form action="user_save" namespace="/">
			<div class="ItemBlock_Title1">
				<!-- 信息说明 -->
				<div class="ItemBlock_Title1">
					<img border="0" width="4" height="7"
						src="${pageContext.request.contextPath}/jsps/style/blue/images/item_point.gif" />
					用户信息
				</div>
			</div>

			<!-- 表单内容显示 -->
			<div class="ItemBlockBorder">
				<div class="ItemBlock">
					<table cellpadding="0" cellspacing="0" class="mainForm">
						<tr>
							<td width="100">所属部门</td>
							<td><s:select name="department.id" cssClass="SelectStyle"
									list="departments" listKey="id" listValue="name" headerKey="0"
									headerValue="请选择部门"></s:select></td>
						</tr>
						<tr>
							<td>登录名</td>
							<td><input type="text" name="loginName" class="InputStyle" />
								* （登录名要唯一）
								<span id="Msg"></span>
								</td>
						</tr>
						<tr>
							<td>姓名</td>
							<td><input type="text" name="name" class="InputStyle" /> *</td>
						</tr>
						<tr>
							<td>性别</td>
							<td><input type="RADIO" name="sex" value="1" id="male" /><label
								for="male">男</label> <input type="RADIO" name="sex" value="0"
								id="female" /><label for="female">女</label></td>
						</tr>
						<tr>
							<td>联系电话</td>
							<td><input type="text" name="telephone" class="InputStyle" /></td>
						</tr>
						<tr>
							<td>E-mail</td>
							<td><input type="text" name="email" class="InputStyle" /></td>
						</tr>
						<tr>
							<td>备注</td>
							<td><textarea name="description" class="TextareaStyle"></textarea></td>
						</tr>
					</table>
				</div>
			</div>

			<div class="ItemBlock_Title1">
				<!-- 信息说明 -->
				<div class="ItemBlock_Title1">
					<img border="0" width="4" height="7"
						src="${pageContext.request.contextPath}/jsps/style/blue/images/item_point.gif" />
					岗位设置
				</div>
			</div>

			<!-- 表单内容显示 -->
			<div class="ItemBlockBorder">
				<div class="ItemBlock">
					<table cellpadding="0" cellspacing="0" class="mainForm">
						<tr>
							<td width="100">岗位</td>
							<td>
							
							<s:select name="roleIds" multiple="true" size="10"
									cssClass="SelectStyle" list="roles" listKey="id" listValue="name"/>
								按住Ctrl键可以多选或取消选择</td>
						</tr>
					</table>
				</div>
			</div>

			<!-- 表单操作 -->
			<div id="InputDetailBar">
				<input type="image" id="save"
					src="${pageContext.request.contextPath}/jsps/style/images/save.png" />
				<a href="javascript:history.go(-1);"><img
					src="${pageContext.request.contextPath}/jsps/style/images/goBack.png" /></a>
			</div>
		</s:form>
	</div>

	<div class="Description">
		说明：<br /> 1，用户的登录名要唯一，在填写时要同时检测是否可用。<br /> 2，新建用户后，密码被初始化为"1234"。<br />
		3，密码在数据库中存储的是MD5摘要（不是存储明文密码）。<br /> 4，用户登录系统后可以使用“个人设置→修改密码”功能修改密码。<br />
		5，新建用户后，会自动指定默认的头像。用户可以使用“个人设置→个人信息”功能修改自已的头像<br />
		6，修改用户信息时，登录名不可修改。
	</div>

</body>
</html>
