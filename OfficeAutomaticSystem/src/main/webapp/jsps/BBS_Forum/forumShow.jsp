<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<title>【${name}】中的主题列表</title>
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
</head>
<body>
	<s:debug />
	<!-- 标题显示 -->
	<div id="Title_bar">
		<div id="Title_bar_Head">
			<div id="Title_Head"></div>
			<div id="Title">
				<!--页面标题-->
				<img border="0" width="13" height="13"
					src="${pageContext.request.contextPath}/jsps/style/images/title_arrow.gif" />
				【常见问题】中的主题列表
			</div>
			<div id="Title_End"></div>
		</div>
	</div>

	<div id="MainArea">
		<div id="PageHead"></div>
		<center>
			<div class="ItemBlock_Title1" style="width: 98%;">
				<font class="MenuPoint"> &gt; </font> <a
					href="${pageContext.request.contextPath}/forum_list.action">论坛</a>
				<font class="MenuPoint"> &gt; </font> ${name} <span
					style="margin-left:30px;"><a
					href="${pageContext.request.contextPath}/topic_saveUI.action?forum.id=${id}">
						<img align="absmiddle"
						src="${pageContext.request.contextPath}/jsps/style/blue/images/button/publishNewTopic.png" />
				</a> </span>
			</div>

			<div class="ForumPageTableBorder">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<!--表头-->
					<tr align="center" valign="middle">
						<td width="3" class="ForumPageTableTitleLeft"><img border="0"
							width="1" height="1"
							src="${pageContext.request.contextPath}/jsps/style/images/blank.gif" />
						</td>
						<td width="50" class="ForumPageTableTitle">
							<!--状态/图标-->&nbsp;
						</td>
						<td class="ForumPageTableTitle">主题</td>
						<td width="130" class="ForumPageTableTitle">作者</td>
						<td width="100" class="ForumPageTableTitle">回复数</td>
						<td width="130" class="ForumPageTableTitle">最后回复</td>
						<td width="3" class="ForumPageTableTitleRight"><img
							border="0" width="1" height="1"
							src="${pageContext.request.contextPath}/jsps/style/images/blank.gif" />
						</td>
					</tr>
					<tr height="1" class="ForumPageTableTitleLine">
						<td colspan="8"></td>
					</tr>
					<tr height=3>
						<td colspan=8></td>
					</tr>

					<!--主题列表-->
					<tbody class="dataContainer" datakey="topicList">
						<s:iterator value="recordList">
							<tr height="35" id="d0" class="template">
								<td></td>
								<td class="ForumTopicPageDataLine" align="center"><img
									src="${pageContext.request.contextPath}/jsps/style/images/topicType_${type}.gif" /></td>
								<td class="Topic"><a class="Default"
									href="${pageContext.request.contextPath}/topic_show.action?id=${id}">${title}</a></td>
								<td class="ForumTopicPageDataLine" align="center">
									<ul class="ForumPageTopicUl">
										<li class="Author">${author.name}</li>
										<li class="CreateTime"><s:date name="postTime"
												format="yyyy-MM-dd hh:mm:ss" /></li>
									</ul>
								</td>
								<td class="ForumTopicPageDataLine Reply" align="center"><b>${replyCount}</b></td>
								<td class="ForumTopicPageDataLine" align="center">
									<ul class="ForumPageTopicUl">
										<li class="Author">${lastReply.author.name}</li>
										<li class="CreateTime"><s:date
												name="lastReply.createTime" format="yyyy-MM-dd hh:mm:ss" />
										</li>
									</ul>
								</td>
								<td></td>
							</tr>
						</s:iterator>
					</tbody>
					<!--主题列表结束-->

					<tr height="3">
						<td colspan="9"></td>
					</tr>

				</table>

				<!--其他操作-->
				<s:form action="forum_show" method="post" namespace="/"
					id="paginationForm">
					<div id="TableTail">
						<div id="TableTail_inside">
							<table border="0" cellspacing="0" cellpadding="0" height="100%"
								align="left">
								<s:hidden name="id"></s:hidden>
								<tr valign=bottom>
									<td></td>
									<td><select name="viewType">
											<option value="0">全部主题</option>
											<option value="1">全部精华贴</option>
									</select> <select name="orderBy">
											<option value="0">默认排序（按最后更新时间排序，但所有置顶帖都在前面）</option>
											<option value="1">按最后更新时间排序</option>
											<option value="2">按主题发表时间排序</option>
											<option value="3">按回复数量排序</option>
									</select> <select name="reverse" id="reverse">
											<option value="true">降序</option>
											<option value="false">升序</option>
									</select> <input type="IMAGE"
										src="${pageContext.request.contextPath}/jsps/style/blue/images/button/submit.PNG"
										align="ABSMIDDLE" /></td>
								</tr>
							</table>
						</div>
					</div>
				</s:form>
				<!--分页信息-->
				<%@include file="/jsps/common/paginationBar.jsp"%>
				<script type="text/javascript">
					$("select[name='viewType']").val("${viewType}");
					$("select[name='orderBy']").val("${orderBy}");
					$("select[name='reverse']").val("${reverse}");
				</script>
			</div>
		</center>
	</div>

	<div class="Description">
		说明：<br /> 1，主题默认按最后更新的时间降序排列。最后更新时间是指主题最后回复的时间，如果没有回复，就是主题发表的时间。<br />
		2，帖子有普通、置顶、精华之分。置顶贴始终显示在最上面，精华贴用不同的图标标示。<br />
	</div>

</body>
</html>
