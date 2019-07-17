package org.eric.sessionclient.core;

import org.eric.sessionclient.bean.ActionType;
import org.eric.sessionclient.bean.DeliverySessionCreationType;
import org.eric.sessionclient.core.request.IPostRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.*;

public class ConnectManage {
    private static final Logger logger = LoggerFactory.getLogger(ConnectManage.class);

    //存放已开始的session
    private static Map<Long, DeliverySessionCreationType> runningSessionMap = new ConcurrentHashMap<Long, DeliverySessionCreationType>();

    //存放要执行stop的请求
    public static ScheduledExecutorService stopRequestPool = Executors.newScheduledThreadPool(2<<10);

    //存放等待执行stop的runnableMap
    private static Map<Long, ScheduledFuture<?>> futureMap = new ConcurrentHashMap<Long, ScheduledFuture<?>>();


    public static void addToSessionMap(DeliverySessionCreationType session){
        if (session != null){
            runningSessionMap.put(session.getDeliverySessionId(), session);
        }
    }

    public static void removeFromSessionMap(Long sessionId){
        runningSessionMap.remove(sessionId);
    }

    public static void removeFromFutureMap(Long sessionId){
        futureMap.remove(sessionId);
    }

    public static void addToPool(final String url, final DeliverySessionCreationType session, long delay, IPostRequest request){
        session.setAction(ActionType.STOP);
        Runnable task = () -> request.sessionStop(url, session);
        ScheduledFuture<?> schedule = stopRequestPool.schedule(task, delay, TimeUnit.MILLISECONDS);
        futureMap.put(session.getDeliverySessionId(), schedule);
//        try {
//            stopRequestPool.shutdown();
//            stopRequestPool.awaitTermination(Long.MAX_VALUE, TimeUnit.MINUTES);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
