package cn.itcast.ssh.web;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class MyRequest extends HttpServletRequestWrapper {
	private HttpServletRequest request = null;
	private boolean flag = false;

	public MyRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
	}

	// 重新getParemeter方法
	@Override
	public String getParameter(String name) {
		// TODO Auto-generated method stub
		Map<String, String[]> parameterMap = getParameterMap();
		String value = parameterMap.get(name)[0];
		System.out.println(value + "getParameter");
		return value;
	}

	// 重新getparametervalues方法
	@Override
	public String[] getParameterValues(String name) {
		// TODO Auto-generated method stub
		Map<String, String[]> parameterMap = getParameterMap();
		String[] values = parameterMap.get(name);
		return values;
	}

	// 重新getparametermap方法

	@Override
	public Map<String, String[]> getParameterMap() {
		// TODO Auto-generated method stub
		Map<String, String[]> map = request.getParameterMap();
		if (!flag) {
			Collection<String[]> values = map.values();
			for (String[] strings : values) {
				for (int i = 0; i < strings.length; i++) {
					try {
						// 将get方式的请求的字符数据先编码在解码
						System.out.println(strings[i] + "之前");
						System.out.println(Arrays.toString(strings[i]
								.getBytes("iso-8859-1")));
						strings[i] = new String(
								strings[i].getBytes("iso-8859-1"), "utf-8");
						System.out.println(strings[i] + "之后");
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						throw new RuntimeException(e);
					}
				}
			}
			flag = true;
		}
		return map;
	}
}
