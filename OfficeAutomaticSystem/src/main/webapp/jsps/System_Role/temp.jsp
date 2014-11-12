<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>配置权限</title>
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
<script language="javascript"
	src="${pageContext.request.contextPath}/jsps/script/jquery_treeview/jquery.treeview.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jsps/script/zTree/jquery.ztree.all-3.5.js"></script>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/jsps/style/blue/file.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/jsps/script/jquery_treeview/jquery.treeview.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/jsps/script/zTree/zTreeStyle.css" />
<script type="text/javascript">
	
	var setting = {
			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback:{
				onClick:zTreeOnClick
			}
		};

		
		function zTreeOnClick(event, treeId, treeNode) {
			var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		    //alert(treeNode.tId + ", " + treeNode.name+","+treeNode.checked);
		   // alert(treeNode.id);
		    if(treeNode.checked==true){
		    	//alert("aa");
		    	treeObj.checkNode(treeNode,false,true);
		    	
		    	//
		    	
		    	var treeNodes=treeObj.getCheckedNodes(true);
		    		
		    	//alert(treeNodes.length);
		    	$("#div").html("");
		    	for(var i=0;i<treeNodes.length;i++){
		    		$("#div").append("<input type='hidden' name='privilegeIds' value='"+treeNodes[i].id+"'></input>");
		    	}
		    	
		    }else{
		    	//alert("bbb");
		    	treeObj.checkNode(treeNode,true,true);
		    	
		    	var treeNodes=treeObj.getCheckedNodes(true);
		    	$("#div").html("");
		    	//alert(treeNodes.length);
		    	for(var i=0;i<treeNodes.length;i++){
		    		$("#div").append("<input type='hidden' name='privilegeIds' value='"+treeNodes[i].id+"'></input>");
		    	}
		    	
		    }
		    
		};
	
		$(function(){
			
			$.get("${pageContext.request.contextPath}/role_setPrivilege2.action",function(data){
				$.fn.zTree.init($("#treeDemo"), setting, data);
				
			var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
			
			//获取全部节点
			
			var allNodes = treeObj.transformToArray(treeObj.getNodes());
				privilegeIds=new Array();
				 //privilegeIds="${privilegeIds}";
								
				// var size=<%=((Long[])request.getAttribute("privilegeIds")).length%>
				// privilegeIds=eval("("+"${privilegeIds}"+")");

				 
				 <% Long[] pIds= (Long[])request.getAttribute("privilegeIds");
				 	for(Long pId:pIds){
				 	
				 %>
				 
				 	privilegeIds.push(<%=pId%>);
				 
				 <% }%>
				// alert(privilegeIds);
				 //alert(size);
							for (var i = 0; i < allNodes.length; i++) {
								//获取web层传过来的数据privilegeIds
								for (var j = 0; j < privilegeIds.length; j++) {
									if (privilegeIds[j] == allNodes[i].id) {
										//说明是要回显的
										treeObj.checkNode(allNodes[i], true,
												false);
									}
								}
								
								allNodes[i].open=true;
							}

							//将回显的zTree节点生成input
							var treeNodes = treeObj.getCheckedNodes(true);
							$("#div").html("");
							//alert(treeNodes.length);
							for (var i = 0; i < treeNodes.length; i++) {
								$("#div")
										.append(
												"<input type='hidden' name='privilegeIds' value='"+treeNodes[i].id+"'></input>");
							}
						});

	});
</script>
</head>
<body>
	<form
		action="${pageContext.request.contextPath}/role_setPrivilege2.action"
		method="post">
		<ul id="treeDemo" class="ztree"></ul>
		<s:hidden name="id"></s:hidden>
		<s:debug></s:debug>
		<div id="div"></div>
		<s:iterator value="privilegeIds" var="oid">
			${oid}
		</s:iterator>
		<input type="submit">
	</form>
</body>

</html>