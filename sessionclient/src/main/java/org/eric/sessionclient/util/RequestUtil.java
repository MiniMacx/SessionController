package org.eric.sessionclient.util;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.eric.sessionclient.bean.DeliverySessionCreationType;
import org.eric.sessionclient.bean.ObjectFactory;

public class RequestUtil {
    private static final String CHARSET = "UTF-8";

    public static HttpPost buildPost(String xml, String url) {
        HttpPost post = new HttpPost(url);
        post.addHeader("Content-Type", "text/html;charset=" + CHARSET);
        StringEntity stringEntity = new StringEntity(xml, CHARSET);
        stringEntity.setContentEncoding(CHARSET);
        post.setEntity(stringEntity);
        return post;
    }

    public static String buildXmlString(DeliverySessionCreationType deliverySessionCreationType) {
        return new JaxUtil(DeliverySessionCreationType.class).toXml(ObjectFactory.createDeliverySession(deliverySessionCreationType));
    }
}
