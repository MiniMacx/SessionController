package org.eric.sessionclient.core.request;

import org.eric.sessionclient.bean.DeliverySessionCreationType;

public interface IPostRequest {
    /**
     * 开始一个session
     * @param url
     * @param session
     */
    void sessionStart(String url, DeliverySessionCreationType session);

    /**
     * 停止一个session
     * @param url
     * @param session
     */
    void sessionStop(String url, DeliverySessionCreationType session);
}
