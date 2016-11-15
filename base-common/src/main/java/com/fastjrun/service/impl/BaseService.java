package com.fastjrun.service.impl;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;

public abstract class BaseService {
	protected final Log log = LogFactory.getLog(this.getClass());

	@Resource
	protected MessageSource serviceMessageSource;

}
