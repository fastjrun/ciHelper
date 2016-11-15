package com.fastjrun.web.controller;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fastjrun.packet.v11.BaseApiRequestHeadV11;

public class BaseControllerV11 {

    @Autowired
    protected ServletContext servletContext;

    protected final Log log = LogFactory.getLog(this.getClass());

    protected void processHead(BaseApiRequestHeadV11 head) {

        log.debug("head=" + head);

    }

}
