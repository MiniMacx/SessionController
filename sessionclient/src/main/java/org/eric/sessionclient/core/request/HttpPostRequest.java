package org.eric.sessionclient.core.request;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.eric.sessionclient.bean.DeliverySessionCreationType;
import org.eric.sessionclient.core.handler.SessionResponseHandler;
import org.eric.sessionclient.util.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 普通Http请求
 */
public class HttpPostRequest implements IPostRequest {

    private static volatile HttpPostRequest request;

    private static final Logger logger = LoggerFactory.getLogger(HttpPostRequest.class);

    private HttpPostRequest(){}

    public static HttpPostRequest getInstance(){
        if (request == null){
            synchronized (HttpPostRequest.class){
                if (request == null){
                    request = new HttpPostRequest();
                }
            }
        }
        return request;
    }

    public void sessionStart(String url, DeliverySessionCreationType session) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        final String xml = RequestUtil.buildXmlString(session);
        HttpPost httpPost = RequestUtil.buildPost(xml, url);
        ResponseHandler<String> responseHandler = httpResponse -> SessionResponseHandler.handleStart(httpResponse, session, url, request);
        try {
            httpClient.execute(httpPost, responseHandler);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    public void sessionStop(String url, DeliverySessionCreationType session) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String xml = RequestUtil.buildXmlString(session);
        HttpPost httpPost = RequestUtil.buildPost(xml, url);
        try {
            httpClient.execute(httpPost, httpResponse -> SessionResponseHandler.handleStop(httpResponse, session));
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}