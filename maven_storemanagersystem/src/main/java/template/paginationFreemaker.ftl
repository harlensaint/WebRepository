<div align="right">
						
		<#if (page>1)>
			<a  href="${requestUrl}?page=1&${paramsUrl}">首页</a>
			<a href="${requestUrl}?page=${page-1 }&${paramsUrl}">上一页</a>
		</#if>
		
		<#list begin..end as i>
			<a href="${requestUrl}?page=${i}&${paramsUrl}" >
			
			<#if i==page>
			<font color='blue'>[${i}]</font>
			
			<#else>[${i}]
			</#if>
			</a>
		</#list>
		
		<#if (page<totalPage)>
			<a  href="${requestUrl}?page=${totalPage}&${paramsUrl}">未页</a>
			<a href="${requestUrl}?page=${page+1 }&${paramsUrl}">下一页</a>
		</#if>
				<input type="text" size="2" name="page" /> 
				<input type="button" value="go" size="2" />
		</div>
		<#--模板需要的参数：page当前页，requestUrl请求的url路径，paramsUrl查询条件，begin开始页，end结束页 ,totalPage总页数-->