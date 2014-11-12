<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>导航菜单</title>
<script language="JavaScript"
	src="${pageContext.request.contextPath}/jsps/script/jquery.js"></script>
<script language="JavaScript"
	src="${pageContext.request.contextPath}/jsps/script/menu.js"></script>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/jsps/style/blue/menu.css" />
</head>
<body style="margin: 0">
	<div id="Menu">
		<s:iterator value="#application.topPrivileges">
			<s:if test="#session.loginUser.checkPrivileges(name)">
				<ul id="MenuUl">
					<li class="level1">
						<div onClick="menuClick(this);" class="level1Style">
							<img
								src="${pageContext.request.contextPath}/jsps/style/images/MenuIcon/FUNC20082.gif"
								class="Icon" /> ${name}
						</div> <s:iterator value="children">
							<s:if test="#session.loginUser.checkPrivileges(name)">
								<ul style="display: none;" class="MenuLevel2">
									<li class="level2">
										<div class="level2Style">
											<img
												src="${pageContext.request.contextPath}/jsps/style/images/MenuIcon/menu_arrow_single.gif" />
											<a target="right"
												href="${pageContext.request.contextPath}${url}.action">${name}</a>
										</div>
									</li>
								</ul>
							</s:if>
						</s:iterator>
					</li>
				</ul>
			</s:if>
		</s:iterator>
	</div>
</body>
</html>
