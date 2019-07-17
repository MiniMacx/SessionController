package org.eric.sessionclient.core.request;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.nio.reactor.ConnectingIOReactor;
import org.apache.http.nio.reactor.IOReactorException;
import org.eric.sessionclient.bean.DeliverySessionCreationType;
import org.eric.sessionclient.core.handler.SessionResponseHandler;
import org.eric.sessionclient.util.RequestUtil;

/**
 * 异步http请求
 */
public class AsyncHttpPostRequest implements IPostRequest {

    private static final int CONNECT_TIME_OUT = 3000;

    private static final int CONN_TOTAL = 4000;

    private static volatile AsyncHttpPostRequest request;

    private AsyncHttpPostRequest(){}

    public static AsyncHttpPostRequest getInstance(){
        if (request == null){
            synchronized (HttpPostRequest.class){
                if (request == null){
                    request = new AsyncHttpPostRequest();
                }
            }
        }
        return request;
    }

    private static RequestConfig requestConfig = RequestConfig.custom()
            .setConnectTimeout(CONNECT_TIME_OUT)
            .setSocketTimeout(CONNECT_TIME_OUT)
            .setConnectionRequestTimeout(CONNECT_TIME_OUT)
            .build();

//    private static IOReactorConfig ioReactorConfig = IOReactorConfig.custom().
//            setIoThreadCount(Runtime.getRuntime().availableProcessors())
//            .setSoKeepAlive(false)
//            .build();

    public void sessionStart(String url, DeliverySessionCreationType session) {
        execute(url, session, new FutureCallback<HttpResponse>() {
            @Override
            public void completed(HttpResponse httpResponse) {
                SessionResponseHandler.handleStart(httpResponse, session, url, request);
            }

            @Override
            public void failed(Exception e) {
                SessionResponseHandler.handleFailed(e);
            }

            @Override
            public void cancelled() {
                SessionResponseHandler.handleCancelled();
            }
        });
    }

    public void sessionStop(String url, DeliverySessionCreationType session) {
        execute(url, session, new FutureCallback<HttpResponse>() {
            @Override
            public void completed(HttpResponse httpResponse) {
                SessionResponseHandler.handleStop(httpResponse, session);

            }

            @Override
            public void failed(Exception e) {
                SessionResponseHandler.handleFailed(e);
            }

            @Override
            public void cancelled() {
                SessionResponseHandler.handleCancelled();
            }
        });
    }

    private static void execute(String url, DeliverySessionCreationType session, FutureCallback<HttpResponse> callback) {
        PoolingNHttpClientConnectionManager connManager = buildConnManager();
        final CloseableHttpAsyncClient client = HttpAsyncClients.custom().
                setConnectionManager(connManager)
                .setDefaultRequestConfig(requestConfig)
                .build();
        String xml = RequestUtil.buildXmlString(session);
        HttpPost httpPost = RequestUtil.buildPost(xml, url);
        client.start();
        client.execute(httpPost, callback);
    }

    private static PoolingNHttpClientConnectionManager buildConnManager() {
        ConnectingIOReactor ioReactor;
        try {
            ioReactor = new DefaultConnectingIOReactor();
            PoolingNHttpClientConnectionManager connManager = new PoolingNHttpClientConnectionManager(ioReactor);
            connManager.setMaxTotal(CONN_TOTAL);
            connManager.setDefaultMaxPerRoute(CONN_TOTAL);
            return connManager;
        } catch (IOReactorException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
