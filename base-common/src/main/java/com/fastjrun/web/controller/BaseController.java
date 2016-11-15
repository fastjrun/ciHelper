package com.fastjrun.web.controller;

import java.util.Properties;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fastjrun.common.RestException;
import com.fastjrun.packet.req.RequestHead;

public class BaseController {

    @Autowired
    protected ServletContext servletContext;

    @Autowired
    private Properties codeMsgProperties;

    protected final Log log = LogFactory.getLog(this.getClass());

    /**
     * 
     * @Title: createRE @Description: 创建一个rest异常 @param @param
     * code错误编码 @param @return @return RestException @throws
     */
    protected RestException createRE(String code) {
        return new RestException(code, (String) codeMsgProperties.get(code));
    }

    /**
     * 
     * @Title: createRE @Description: 创建一个rest异常 @param @param code
     * 错误编码 @param @param msg 错误信息 @param @return @return RestException @throws
     */
    protected RestException createRE(String code, String msg) {
        return new RestException(code, msg);
    }

    protected void processHead(RequestHead head) {

        log.debug("head=" + head);

    }

}
