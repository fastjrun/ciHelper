package com.fastjrun.task;

import java.util.concurrent.Semaphore;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fastjrun.common.TaskException;

@Component
public class ExampleTask extends BaseTask {

    //@Value("${exampleTask。semaphoreTtotal}")
    protected int semaphoreTtotal;

    @Override
    public void process() throws TaskException {
        final Semaphore semaphore = new Semaphore(this.semaphoreTtotal);
        while (true) {
            // 获取许可
            try {
                semaphore.acquire();
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                continue;
            }
            Runnable run = new Runnable() {

                public void run() {
                    log.info("start:" + Thread.currentThread().getId());
                    try {
                        Thread.sleep(30000l);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    log.info("end" + Thread.currentThread().getId());
                    // 访问完后，释放
                    semaphore.release();
                }

            };
            this.executorService.submit(run);

        }

    }
}
