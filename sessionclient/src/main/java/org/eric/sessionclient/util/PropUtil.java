package org.eric.sessionclient.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropUtil {
    private static Logger logger = LoggerFactory.getLogger(PropUtil.class);

    private static final String FILE_PATH = PropUtil.class.getResource("/").getPath() + "application.properties";

    private static Properties properties = new Properties();

    public static String getProperty(String propName) {
        if (StringUtils.isEmpty(propName)) {
            logger.error("empty property name!");
            throw new RuntimeException();
        }
        try {
            String property;
            InputStream in = new FileInputStream(FILE_PATH);
            properties.load(in);
            property = properties.getProperty(propName);
            if (StringUtils.isEmpty(property)) {
                throw new RuntimeException("cannot get property: " + propName);
            }
            return property;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
