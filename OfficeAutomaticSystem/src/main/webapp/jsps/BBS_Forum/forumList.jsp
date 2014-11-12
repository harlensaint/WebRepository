<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>论坛</title>
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
				论坛
			</div>
			<div id="Title_End"></div>
		</div>
	</div>
	<div id="MainArea">
		<center>
			<div class="ForumPageTableBorder" style="margin-top: 25px;">

				<table width="100%" border="0" cellspacing="0" cellpadding="0">

					<!--表头-->
					<tr align="center" valign="middle">
						<td colspan="3" class="ForumPageTableTitleLeft">版块</td>
						<td width="80" class="ForumPageTableTitle">主题数</td>
						<td width="80" class="ForumPageTableTitle">文章数</td>
						<td width="270" class="ForumPageTableTitle">最后发表的主题</td>
					</tr>
					<tr height="1" class="ForumPageTableTitleLine">
						<td colspan="9"></td>
					</tr>
					<tr height="3">
						<td colspan="9"></td>
					</tr>

					<!--版面列表-->
					<s:iterator value="forums">
						<tbody class="dataContainer" datakey="forumList">
							<tr height="78" align="center" class="template">
								<td width="3"></td>
								<td width="75" class="ForumPageTableDataLine"><img
									src="${pageContext.request.contextPath}/jsps/style/images/forumpage3.gif" /></td>
								<td class="ForumPageTableDataLine">
									<ul class="ForumPageTopicUl">
										<li class="ForumPageTopic"><a class="ForumPageTopic"
											href="${pageContext.request.contextPath}/forum_show.action?id=${id}">${name}</a></li>
										<li class="ForumPageTopicMemo">${description}</li>
									</ul>
								</td>
								<td class="ForumPageTableDataLine"><b>${topicCount}</b></td>
								<td class="ForumPageTableDataLine"><b>${articleCount}</b></td>
								<td class="ForumPageTableDataLine">
									<ul class="ForumPageTopicUl">
										<li><font color="#444444">┌ 主题：</font> <a
											class="ForumTitle"
											href="${pageContext.request.contextPath}/jsps/BBS_Topic/topicShow.html">${lastTopic.title}</a>
										</li>
										<li><font color="#444444">├ 作者：</font>
											${lastTopic.author.name}</li>
										<li><font color="#444444">└ 时间：</font>
											<s:date name="lastTopic.postTime" format="yyyy-MM-dd hh:mm:ss"/>
											</li>
									</ul>
								</td>
								<td width="3"></td>
							</tr>
						</tbody>
					</s:iterator>
					<!-- 版面列表结束 -->

					<tr height="3">
						<td colspan="9"></td>
					</tr>
				</table>

			</div>
		</center>
	</div>
</body>
</html>
