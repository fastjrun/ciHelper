package com.fastjrun.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

@ContextConfiguration(locations = { "classpath*:applicationContext.xml" })
public abstract class BaseSpringTestNGTest extends AbstractTestNGSpringContextTests {
    protected final Log log = LogFactory.getLog(this.getClass());

}
