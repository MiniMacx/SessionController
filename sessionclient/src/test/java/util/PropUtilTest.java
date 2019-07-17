package util;

import org.eric.sessionclient.util.PropUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class PropUtilTest {

    @Test
    public void getPropertyTest(){
        String async = PropUtil.getProperty("async");
        assert async.equals("0");
    }
}
