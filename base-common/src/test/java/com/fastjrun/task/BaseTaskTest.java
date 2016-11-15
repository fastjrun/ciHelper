package com.fastjrun.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.fastjrun.test.BaseSpringTestNGTest;

public class BaseTaskTest extends BaseSpringTestNGTest {

    @Autowired
    ExampleTask exampleTask;

    @Test
    public void process() {
        exampleTask.process();
    }
}
