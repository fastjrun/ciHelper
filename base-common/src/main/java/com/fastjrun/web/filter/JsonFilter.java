package com.fastjrun.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JsonFilter implements Filter {
	private static final Log log = LogFactory.getLog(JsonFilter.class);
	public void init(FilterConfig initconfig) throws ServletException {

	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		String reqMsg = IOUtils.toString(request.getInputStream());
		String reqestUrl = request.getRequestURI();
		log.info("reqMsg is :\t"+reqestUrl+"\t"+reqMsg);
		chain.doFilter(req, res);
	}

	public void destroy() {

	}
}
