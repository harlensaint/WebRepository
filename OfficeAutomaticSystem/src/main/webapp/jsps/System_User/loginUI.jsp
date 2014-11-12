<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="/struts-tags"  prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <META http-equiv=Content-Type CONTENT="text/html; charset=gbk" />
	<TITLE>Itcast OA</TITLE>
	<LINK HREF="${pageContext.request.contextPath}/jsps/style/blue/login.css" type=text/css rel=stylesheet />
	<script language="javascript"
	src="${pageContext.request.contextPath}/jsps/script/jquery.js"></script>
	<script type="text/javascript">
	
	$(function(){
		$("input[name='loginName']").focus();
		//alert(window);
		if(window.parent!=window){
			window.parent.location.href="${pageContext.request.contextPath}/jsps/System_User/loginUI.jsp";
		}
	});
	
	</script>
</HEAD>

<BODY LEFTMARGIN=0 TOPMARGIN=0 MARGINWIDTH=0 MARGINHEIGHT=0 CLASS=PageBody >
<FORM METHOD="post" NAME="actForm" ACTION="${pageContext.request.contextPath}/user_login.action"  target="_top">
    <DIV ID="CenterAreaBg">
        <DIV ID="CenterArea">
            <DIV ID="LogoImg"><IMG BORDER="0" SRC="${pageContext.request.contextPath}/jsps/style/blue/images/logo.png" /></DIV>
            <DIV ID="LoginInfo">
                <TABLE BORDER=0 CELLSPACING=0 CELLPADDING=0 width=100%>
                <tr>
                <td colspan="3">
                	<font color="red"><s:actionerror/></font>
                </td>
                </tr>
                    <TR>
                        <TD width=45 CLASS="Subject"><IMG BORDER="0" SRC="${pageContext.request.contextPath}/jsps/style/blue/images/login/userId.gif" /></TD>
                        <TD><INPUT SIZE="20" CLASS="TextField" TYPE="text" NAME="loginName" /></TD>
                        <TD ROWSPAN="2" STYLE="padding-left:10px;"><INPUT TYPE="image" SRC="${pageContext.request.contextPath}/jsps/style/blue/images/login/userLogin_button.gif"/></TD>
                    </TR>
                    <TR>
                        <TD CLASS="Subject"><IMG BORDER="0" SRC="${pageContext.request.contextPath}/jsps/style/blue/images/login/password.gif" /></TD>
                        <TD><INPUT SIZE="20" CLASS="TextField" TYPE="password" NAME="password" /></TD>
                    </TR>
                </TABLE>
            </DIV>
            <DIV ID="CopyRight"><A HREF="javascript:void(0)">&copy; 2010 版权所有 itcast</A></DIV>
        </DIV>
    </DIV>
</FORM>
</BODY>

</HTML>

