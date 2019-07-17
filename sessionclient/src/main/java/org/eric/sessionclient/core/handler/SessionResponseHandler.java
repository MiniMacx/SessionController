package org.eric.sessionclient.core.handler;

import org.apache.http.HttpResponse;
import org.eric.sessionclient.bean.DeliverySessionCreationType;
import org.eric.sessionclient.core.ConnectManage;
import org.eric.sessionclient.core.request.IPostRequest;
import org.eric.sessionclient.util.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionResponseHandler {

    public static final Logger logger = LoggerFactory.getLogger(SessionResponseHandler.class);

    public static String handleStart(HttpResponse httpResponse, DeliverySessionCreationType session, String url, IPostRequest request){
        int status = httpResponse.getStatusLine().getStatusCode();
        if (status >= 200 && status < 300) {
            logger.info("\nsession started!\n" +
                    "\tsessionId:{}\n" +
                    "\turl:{}\n" +
                    "\tsendTime:{}\n" +
                    "\tbody:{}\n", session.getDeliverySessionId(), url, session.getStartTime(), RequestUtil.buildXmlString(session));
            ConnectManage.addToPool(url, session, session.getStopTime() - session.getStartTime(), request);
            ConnectManage.addToSessionMap(session);
        } else {
            logger.error("what's your problem? : {}", status);
        }
        return null;
    }

    public static String handleStop(HttpResponse httpResponse, DeliverySessionCreationType session){
        long id = session.getDeliverySessionId();
        int status = httpResponse.getStatusLine().getStatusCode();
        if (status >= 200 && status < 300) {
            ConnectManage.removeFromFutureMap(id);
            ConnectManage.removeFromSessionMap(id);
            logger.info("session stopped:{}", id);
        } else {
            logger.error("what's your problem? : {}", status);

        }
        return null;
    }

    public static void handleFailed(Exception e){
        e.printStackTrace();
    }

    public static void handleCancelled(){
        logger.error("connection cancelled");
    }
}
