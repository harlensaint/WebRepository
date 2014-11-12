<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--分页信息-->
<div id=PageSelectorBar>
	<div id=PageSelectorMemo>
		页次：
		<s:if test="totalCount==0">0</s:if>
		<s:else>${currentPage}</s:else>
		/${pageCount}页 &nbsp; 每页显示：${pageSize}条 &nbsp; 总记录数：${totalCount}条
	</div>
	<div id=PageSelectorSelectorArea>
		<!--
				<IMG SRC="${pageContext.request.contextPath}/jsps/style/blue/images/pageSelector/firstPage2.png"/>
				-->
		<a href="#" onclick="gotoPageNum(1);" title="首页"
			style="cursor:pointer;"> <img
			src="${pageContext.request.contextPath}/jsps/style/blue/images/pageSelector/firstPage.png" />
		</a>

		<s:iterator begin="begin" end="end" var="pageIndex">
			<s:if test="currentPage==#pageIndex">
				<span class="PageSelectorNum PageSelectorSelected"><font
					color="blue">${pageIndex}</font></span>
			</s:if>
			<s:else>
				<span class="PageSelectorNum" style="cursor:pointer;"
					onClick="gotoPageNum(${pageIndex});">${pageIndex}</span>
			</s:else>
		</s:iterator>
		<!--
				<IMG SRC="${pageContext.request.contextPath}/jsps/style/blue/images/pageSelector/lastPage2.png"/>
				-->
		<a href="#" onclick="gotoPageNum(${pageCount});" title="尾页"
			style="cursor:pointer;"> <img
			src="${pageContext.request.contextPath}/jsps/style/blue/images/pageSelector/lastPage.png" /></a>

		转到：
		<!-- <input onFocus="this.select();" maxlength="2"
						class="inputStyle" type="text" value="1" name="currPage"
						tabindex="0" /> <input type="submit" name="goBtn" value="Go"
						class="MiddleButtonStyle" /> -->
		<select name="currentPage" onchange="gotoPageNum(this.value);">
			<s:iterator begin="begin" end="end" var="pageIndex">
				<option value="${pageIndex}">${pageIndex}</option>
			</s:iterator>
		</select>
		<script type="text/javascript">
			$("select[name='currentPage']").val(${currentPage});
		</script>
			
		<script type="text/javascript">
			// 创建一个函数，用来发生请求到指定的action
			function gotoPageNum(currentPage) {
				$("#paginationForm")
						.append(
								'<input name="currentPage" value='+currentPage+' type="hidden">');
				$("input[name='currentPage']").val(currentPage);
				$("#paginationForm").submit();
			}
		</script>