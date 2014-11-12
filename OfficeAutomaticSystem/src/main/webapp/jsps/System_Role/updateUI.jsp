<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="/struts-tags"  prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>岗位设置</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script language="javascript"
	src="${pageContext.request.contextPath}/jsps/script/jquery.js"></script>
<script language="javascript"
	src="${pageContext.request.contextPath}/jsps/script/pageCommon.js"
	charset="utf-8"></script>
<script language="javascript"
	src="${pageContext.request.contextPath}/jsps/script/PageUtils.js"
	charset="utf-8"></script>
<script language="javascript"
	src="${pageContext.request.contextPath}/jsps/script/DemoData.js"
	charset="utf-8"></script>
<script language="javascript"
	src="${pageContext.request.contextPath}/jsps/script/DataShowManager.js"
	charset="utf-8"></script>
<script language="javascript"
	src="${pageContext.request.contextPath}/jsps/jqueryvalidation/jquery.metadata.js"
	charset="utf-8"></script>
<script language="javascript"
	src="${pageContext.request.contextPath}/jsps/jqueryvalidation/jquery.validate.js"
	charset="utf-8"></script>
<script language="javascript"
	src="${pageContext.request.contextPath}/jsps/jqueryvalidation/messages_zh.js"
	charset="utf-8"></script>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/jsps/style/blue/pageCommon.css" />
<script type="text/javascript">
	$(function() {
		$("input[name='name']")
				.blur(
						function() {
							//获取到输入的值
							var value = $(this).val();
							$("#Msg").html("");
							
							var roleName="${name}";
							
							if(value!=""&&roleName!=value){
								$
								.post(
										"${pageContext.request.contextPath}/role_findByRoleName.action",
										{
											name : value
										},
										function(data) {
											//alert(data);
											if (data == "1") {
												//说明岗位名称已经存在了
												$("#Msg")
														.html(
																"<font color='red'>该岗位已经存在了！</font>");
												$("input[type='image']")
														.attr("disabled",
																"disabled");
											} else {
												//说明岗位名称不存在，可是使用
												$("#Msg")
														.html(
																"<font color='green'>该岗位还不存在,可以使用！</font>");
												$("input[type='image']")
														.removeAttr(
																"disabled");
											}

										});
							}
							
						});

		//也没一加载时执行校验
		$("form").validate({
			rules : {
				name : "required",

			},
			messages : {
				name : "岗位的名称必须填写！",
			}
		});

	});
</script>
<style type="text/css">
label.error {
	color: red;
}
</style>

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
				岗位设置
			</div>
			<div id="Title_End"></div>
		</div>
	</div>

	<!--显示表单内容-->
	<div id="MainArea">
		<s:form action="role_update"  method="post" namespace="/" theme="simple">
			<div class="ItemBlock_Title1">
				<!-- 信息说明<DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${pageContext.request.contextPath}/jsps/style/blue/images/item_point.gif" /> 岗位信息 </DIV>  -->
			</div>
			
			<!-- 表单内容显示 -->
			<div class="ItemBlockBorder">
				<div class="ItemBlock">
					<table cellpadding="0" cellspacing="0" class="mainForm">
						<s:hidden name="id"></s:hidden>
						<tr>
							<td width="100">岗位名称</td>
							<td>
								<s:textfield type="text" name="name" cssClass="InputStyle"></s:textfield>*
								<span id="Msg"></span></td>
						</tr>
						<tr>
							<td>岗位说明</td>
							<td>
							<s:textarea name="description" cssClass="TextareaStyle"></s:textarea>
						</tr>
					</table>
				</div>
			</div>

			<!-- 表单操作 -->
			<div id="InputDetailBar">
				<input type="image"
					src="${pageContext.request.contextPath}/jsps/style/images/save.png" />
				<a href="javascript:history.go(-1);"><img
					src="${pageContext.request.contextPath}/jsps/style/images/goBack.png" /></a>
			</div>
		</s:form>
	</div>

</body>
</html>
