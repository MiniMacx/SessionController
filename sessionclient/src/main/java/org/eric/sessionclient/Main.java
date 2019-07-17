package org.eric.sessionclient;

import org.eric.sessionclient.bean.ActionType;
import org.eric.sessionclient.bean.DeliverySessionCreationType;
import org.eric.sessionclient.core.request.AsyncHttpPostRequest;
import org.eric.sessionclient.core.request.HttpPostRequest;
import org.eric.sessionclient.util.IDUtil;
import org.eric.sessionclient.util.PropUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 创建线程并发执行
 */
public class Main {
    private static Logger logger = LoggerFactory.getLogger(Main.class);

    private static final String URL = "http://localhost:8081/nbi/deliverysession?id=";

    private static ExecutorService executor = Executors.newFixedThreadPool(2<<8);

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        int async = Integer.parseInt(PropUtil.getProperty("async"));
        int num = Integer.parseInt(PropUtil.getProperty("threadNum"));
        int i=0;
        long cur = System.currentTimeMillis();
        while (i< num){
            executor.submit(()->{
                try {
                    String durTime = PropUtil.getProperty("durTime");
                    long startTime = System.currentTimeMillis();
                    long stopTime = startTime + Long.valueOf(durTime);
                    long id = IDUtil.getId();
                    DeliverySessionCreationType session = DeliverySessionCreationType.Builder.aDeliverySessionCreationType().
                            deliverySessionId(id)
                            .action(ActionType.START)
                            .startTime(System.currentTimeMillis())
                            .stopTime(stopTime)
                            .tmgi("")
                            .tmgiPool("")
                            .build();
                    latch.await();
                    if (async == 0) HttpPostRequest.getInstance().sessionStart(URL + id, session);
                    else AsyncHttpPostRequest.getInstance().sessionStart(URL + id, session);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            ++i;
        }
        latch.countDown();
//        executor.shutdown();
//        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
//        logger.info("cost time: {}", System.currentTimeMillis() - cur);
    }
}
