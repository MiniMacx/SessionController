package util;

import org.apache.commons.lang3.StringUtils;
import org.eric.sessionclient.bean.ActionType;
import org.eric.sessionclient.bean.DeliverySessionCreationType;
import org.eric.sessionclient.bean.ObjectFactory;
import org.eric.sessionclient.util.JaxUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class JaxUtilTest {

    @Test
    public void fromXmlTest(){
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><DeliverySession><DeliverySessionId>5</DeliverySessionId><Action>Start</Action><TMGIPool></TMGIPool><TMGI></TMGI><StartTime>1563374978296</StartTime><StopTime>1563374981294</StopTime></DeliverySession>";
        DeliverySessionCreationType o = new JaxUtil(DeliverySessionCreationType.class).fromXml(xml);
        assert o.getDeliverySessionId() == 5;
    }

    @Test
    public void toXmlTest(){
        DeliverySessionCreationType t = DeliverySessionCreationType.Builder.aDeliverySessionCreationType()
                .action(ActionType.START)
                .deliverySessionId(1)
                .startTime(123L)
                .stopTime(123L)
                .tmgi("")
                .tmgiPool("")
                .build();
        String s = new JaxUtil(DeliverySessionCreationType.class).toXml(ObjectFactory.createDeliverySession(t));
        assert !StringUtils.isEmpty(s);
    }
}
