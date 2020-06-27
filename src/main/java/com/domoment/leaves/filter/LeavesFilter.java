package com.domoment.leaves.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.domoment.leaves.common.util.AccessRecord;
import com.domoment.leaves.common.util.NetworkUtil;


//@Order(Ordered.HIGHEST_PRECEDENCE)//控制过滤器的级别最高
//@WebFilter  此注解不会被发现，不能生成实例加入到spring bean中 或者注册到filter列表
@Component
public class LeavesFilter implements Filter {
	
	private static Logger logger = LoggerFactory.getLogger(LeavesFilter.class);
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletResponse resp = (HttpServletResponse) response;
		String IP = NetworkUtil.getIpAddress((HttpServletRequest) request);
		boolean denied = AccessRecord.access(IP);
		
		if(denied) {
			logger.info("IP: denied access!--" + IP);
			resp.getWriter().print("denid access!");
			resp.sendRedirect("/404.html");
	        return;
		}
		//允许跨域访问，允许访问端设置 header
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");  
		resp.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");  
//		res.setHeader("Access-Control-Max-Age", "0");  
//		res.setHeader("Access-Control-Allow-Credentials", "true");
//		res.setHeader("XDomainRequestAllowed","1");
		chain.doFilter(request, response);

	}

}

