<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<html>
<head>
<title>发表新主题</title>
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
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/jsps/style/blue/forum.css" />

<script language="javascript"
	src="${pageContext.request.contextPath}/jsps/script/ckeditor/ckeditor.js"
	charset="utf-8"></script>
<script type="text/javascript">
	$(function() {
		CKEDITOR.replace('content', {
			uiColor : '#14B8C4',
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
				发表新主题
			</div>
			<div id="Title_End"></div>
		</div>
	</div>

	<!--显示表单内容-->
	<div id="MainArea">
		<form action="${pageContext.request.contextPath}/topic_save.action" style="margin: 0; padding: 0;" method="post">
			<s:hidden name="forum.id" />
			<div id="PageHead"></div>
			<center>
				<div class="ItemBlock_Title1">
					<div width=85% style="float:left">
						<font class="MenuPoint"> &gt; </font> <a
							href="${pageContext.request.contextPath}/forum_list.action">论坛</a>
						<font class="MenuPoint"> &gt; </font> <a
							href="${pageContext.request.contextPath}/forum_show.action?id=${forum.id}">${forum.name }</a>
						<font class="MenuPoint"> &gt;&gt; </font> 发表新主题
					</div>
				</div>
				<div class="ItemBlockBorder">
					<table border="0" cellspacing="1" cellpadding="1" width="100%"
						id="InputArea">
						<tr>
							<td class="InputAreaBg" height="30"><div class="InputTitle">标题</div></td>
							<td class="InputAreaBg"><div class="InputContent">
									<input type="text" name="title" class="InputStyle"
										style="width:100%" />
								</div></td>
						</tr>
						<tr height="240">
							<td class="InputAreaBg"><div class="InputTitle">内容</div></td>
							<td class="InputAreaBg"><div class="InputContent">
									<textarea name="content" id="content"></textarea>
								</div></td>
						</tr>
						<tr height="30">
							<td class="InputAreaBg" colspan="2" align="center"><input
								type="image"
								src="${pageContext.request.contextPath}/jsps/style/blue/images/button/submit.PNG"
								style="margin-right:15px;" /> <a
								href="javascript:history.go(-1);"><img
									src="${pageContext.request.contextPath}/jsps/style/blue/images/button/goBack.png" /></a>
							</td>
						</tr>
					</table>
				</div>
			</center>
		</form>
	</div>

	<div class="Description">
		说明：<br />

	</div>

</body>
</html>
