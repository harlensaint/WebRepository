<%@page import="org.apache.struts2.components.Include"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>查看主题：新手发帖</title>
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
				查看主题
			</div>
			<div id="Title_End"></div>
		</div>
	</div>

	<!--内容显示-->
	<div id="MainArea">
		<div id="PageHead"></div>
		<center>
			<div class="ItemBlock_Title1" style="width: 98%">
				<font class="MenuPoint"> &gt; </font> <a
					href="${pageContext.request.contextPath}/forum_list.action">论坛</a>
				<font class="MenuPoint"> &gt; </font> <a
					href="${pageContext.request.contextPath}/forum_show.action?id=${forum.id}">${forum.name}</a>
				<font class="MenuPoint"> &gt;&gt; </font> 帖子阅读 <span
					style="margin-left:30px;"><a
					href="${pageContext.request.contextPath}/topic_saveUI.action?forum.id=${forum.id}">
						<img align="absmiddle"
						src="${pageContext.request.contextPath}/jsps/style/blue/images/button/publishNewTopic.png" />
				</a> </span>
			</div>

			<div class="ForumPageTableBorder dataContainer" datakey="replyList">

				<!--显示主题标题等-->
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr valign="bottom">
						<td width="3" class="ForumPageTableTitleLeft">&nbsp;</td>
						<td class="ForumPageTableTitle"><b>本帖主题:${title}</b></td>
						<td class="ForumPageTableTitle" align="right"
							style="padding-right:12px;"><a class="detail"
							href="${pageContext.request.contextPath}/reply_saveUI.action?topic.id=${id}"><img
								border="0"
								src="${pageContext.request.contextPath}/jsps/style/images/reply.gif" />回复</a>

						</td>
						<td width="3" class="ForumPageTableTitleRight">&nbsp;</td>
					</tr>
					<tr height="1" class="ForumPageTableTitleLine">
						<td colspan="4"></td>
					</tr>
				</table>

				<!-- ~~~~~~~~~~~~~~~ 显示主帖 ~~~~~~~~~~~~~~~ -->
				<s:if test="currentPage==1">
					<div class="ListArea">
						<table border="0" cellpadding="0" cellspacing="1" width="100%">
							<tr>
								<td rowspan="3" width="130" class="PhotoArea" align="center"
									valign="top">
									<!--作者头像-->
									<div class="AuthorPhoto">
										<img border="0" width="110" height="110"
											src="${pageContext.request.contextPath}/jsps/style/images/defaultAvatar.gif"
											onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/jsps/style/images/defaultAvatar.gif';" />
									</div> <!--作者名称-->
									<div class="AuthorName">
										<s:if test="author.name=='admin'">
								管理员
								</s:if>
										<s:else>
								${author.name }
								</s:else>
									</div>
								</td>
								<td align="center">
									<ul class="TopicFunc">
										<!-- 文章表情与标题 -->
										<li class="TopicSubject">${title }</li>
									</ul>
								</td>
							</tr>
							<tr>
								<!-- 文章内容 -->
								<td valign="top" align="center">
									<div class="Content">${content}</div>
								</td>
							</tr>
							<tr>
								<!--显示楼层等信息-->
								<td class="Footer" height="28" align="center" valign="bottom">
									<ul style="margin: 0px; width: 98%;">
										<li style="float: left; line-height:18px;"><font
											color=#C30000>[楼主]</font> <s:date name="postTime"
												format="yyyy-MM-dd hh:mm:ss" /></li>
										<li style="float: right;"><a
											href="javascript:scroll(0,0)"> <img border="0"
												src="${pageContext.request.contextPath}/jsps/style/images/top.gif" />
										</a></li>
									</ul>
								</td>
							</tr>
						</table>
					</div>
				</s:if>
				<!-- ~~~~~~~~~~~~~~~ 显示主帖结束 ~~~~~~~~~~~~~~~ -->


				<!-- ~~~~~~~~~~~~~~~ 显示回复列表 ~~~~~~~~~~~~~~~ -->
				<div class="ListArea template">
					<s:iterator value="recordList" status="s">
						<table border="0" cellpadding="0" cellspacing="1" width="100%">
							<tr>
								<td rowspan="3" width="130" class="PhotoArea" align="center"
									valign="top">
									<!--作者头像-->
									<div class="AuthorPhoto">
										<img border="0" width="110" height="110"
											src="${pageContext.request.contextPath}/jsps/style/images/defaultAvatar.gif"
											onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/jsps/style/images/defaultAvatar.gif';" />
									</div> <!--作者名称-->
									<div class="AuthorName">
										<s:if test="author.name=='admin'">
								管理员
								</s:if>
										<s:else>
								${author.name }
								</s:else>
									</div>
								</td>
								<td align="center">
									<ul class="TopicFunc">

										<!-- 文章表情与标题 -->
										<li class="TopicSubject">回复：${title}</li>
									</ul>
								</td>
							</tr>
							<tr>
								<!-- 文章内容 -->
								<td valign="top" align="center">
									<div class="Content">${content}</div>
								</td>
							</tr>
							<tr>
								<!--显示楼层等信息-->
								<td class="Footer" height="28" align="center" valign="bottom">
									<ul style="margin: 0px; width: 98%;">
										<li style="float: left; line-height:18px;"><font
											color=#C30000>[<s:property
													value="(currentPage-1)*pageSize+#s.count" />楼]
										</font> <s:date name="createTime" format="yyyy-MM-dd hh:mm:ss" /></li>
										<li style="float: right;"><a
											href="javascript:scroll(0,0)"> <img border="0"
												src="${pageContext.request.contextPath}/jsps/style/images/top.gif" />
										</a></li>
									</ul>
								</td>
							</tr>
						</table>
					</s:iterator>
				</div>
				<!-- ~~~~~~~~~~~~~~~ 显示回复列表结束 ~~~~~~~~~~~~~~~ -->
			</div>

			<%@ include file="/jsps/common/paginationBar.jsp"%>
			
			<form action="${pageContext.request.contextPath }/topic_show.action"
				method="post" id="paginationForm">
				<input type="hidden" name="id" value="${id}" /> 
			</form>
	</div>
	</div>

	<div class="ForumPageTableBorder" style="margin-top: 25px;">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr valign="bottom">
				<td width="3" class="ForumPageTableTitleLeft">&nbsp;</td>
				<td class="ForumPageTableTitle"><b>快速回复</b></td>
				<td width="3" class="ForumPageTableTitleRight">&nbsp;</td>
			</tr>
			<tr height="1" class="ForumPageTableTitleLine">
				<td colspan="3"></td>
			</tr>
		</table>
	</div>
	</center>

	<!--快速回复-->
	<div class="QuictReply">
		<form
			action="${pageContext.request.contextPath}/reply_save.action?topic.id=${id}"
			method="post">
			<div style="padding-left: 3px;">
				<table border="0" cellspacing="1" width="98%" cellpadding="5"
					class="TableStyle">
					<tr class="Tint" height="200">
						<td valign="top" rowspan="2" class="Deep"><b>内容</b></td>
						<td valign="top" class="no_color_bg"><textarea name="content"
								style="width: 95%; height: 300px" id="content"></textarea></td>
					</tr>
					<tr height="30" class="Tint">
						<td class="no_color_bg" colspan="2" align="center"><input
							type="image"
							src="${pageContext.request.contextPath}/jsps/style/blue/images/button/submit.PNG"
							style="margin-right:15px;" /></td>
					</tr>
				</table>
			</div>
		</form>
	</div>
	</div>

	<div class="Description">
		说明：<br /> 1，主帖只在第一页显示。<br />
		2，只有是管理员才可以进行“移动”、“编辑”、“删除”、“精华”、“置顶”的操作。<br /> 3，删除主帖，就会删除所有的跟帖（回复）。<br />
	</div>

</body>
</html>
