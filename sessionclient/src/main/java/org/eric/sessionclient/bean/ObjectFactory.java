
package org.eric.sessionclient.bean;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


@XmlRegistry
public class ObjectFactory {

    private final static QName _DeliverySession_QNAME = new QName("", "DeliverySession");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.eric.bean
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DeliverySessionCreationType }
     * 
     */
    public DeliverySessionCreationType createDeliverySessionCreationType() {
        return new DeliverySessionCreationType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeliverySessionCreationType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "DeliverySession")
    public static JAXBElement<DeliverySessionCreationType> createDeliverySession(DeliverySessionCreationType value) {
        return new JAXBElement<DeliverySessionCreationType>(_DeliverySession_QNAME, DeliverySessionCreationType.class, null, value);
    }

}
