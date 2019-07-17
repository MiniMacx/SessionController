package org.eric.sessionclient.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.*;
import java.io.StringReader;
import java.io.StringWriter;

public class JaxUtil {

    private static final Logger logger = LoggerFactory.getLogger(JaxUtil.class);

    private static String ENCODING = "UTF-8";

    private JAXBContext context;

    public JaxUtil(Class<?> type){
        try {
            context = JAXBContext.newInstance(type);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T fromXml(String xml){
        try {
            StringReader reader = new StringReader(xml);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (T) unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public String toXml(Object root){
        StringWriter writer = new StringWriter();
        try {
            createMarshaller().marshal(root, writer);
            return writer.toString();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    private Marshaller createMarshaller(){
        try {
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, ENCODING);
            return marshaller;
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
    private  Unmarshaller createUnmarshaller(){
        try {
            return context.createUnmarshaller();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
