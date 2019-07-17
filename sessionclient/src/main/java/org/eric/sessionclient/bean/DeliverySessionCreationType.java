

package org.eric.sessionclient.bean;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DeliverySessionCreationType", propOrder = {
    "deliverySessionId",
    "action",
    "tmgiPool",
    "tmgi",
    "startTime",
    "stopTime"
})

@XmlRootElement(name="DeliverySession")
public class DeliverySessionCreationType {

    @XmlElement(name = "DeliverySessionId")
    @XmlSchemaType(name = "unsignedInt")
    protected long deliverySessionId;
    @XmlElement(name = "Action", required = true)
    @XmlSchemaType(name = "string")
    protected ActionType action;
    @XmlElement(name = "TMGIPool")
    protected String tmgiPool;
    @XmlElement(name = "TMGI")
    protected String tmgi;
    @XmlElement(name = "StartTime")
    @XmlSchemaType(name = "unsignedInt")
    protected Long startTime;
    @XmlElement(name = "StopTime")
    @XmlSchemaType(name = "unsignedInt")
    protected Long stopTime;
    @XmlAttribute(name = "Version", required = true)
    protected String version;

    public DeliverySessionCreationType() {
    }



    public long getDeliverySessionId() {
        return deliverySessionId;
    }


    public void setDeliverySessionId(long value) {
        this.deliverySessionId = value;
    }


    public ActionType getAction() {
        return action;
    }


    public void setAction(ActionType value) {
        this.action = value;
    }


    public String getTMGIPool() {
        return tmgiPool;
    }


    public void setTMGIPool(String value) {
        this.tmgiPool = value;
    }


    public String getTMGI() {
        return tmgi;
    }


    public void setTMGI(String value) {
        this.tmgi = value;
    }


    public Long getStartTime() {
        return startTime;
    }


    public void setStartTime(Long value) {
        this.startTime = value;
    }


    public Long getStopTime() {
        return stopTime;
    }


    public void setStopTime(Long value) {
        this.stopTime = value;
    }


    public String getVersion() {
        return version;
    }


    public void setVersion(String value) {
        this.version = value;
    }

    public static final class Builder {
        protected long deliverySessionId;
        protected ActionType action;
        protected String tmgiPool;
        protected String tmgi;
        protected Long startTime;
        protected Long stopTime;
        protected String version;

        private Builder() {
        }

        public static Builder aDeliverySessionCreationType() {
            return new Builder();
        }

        public Builder deliverySessionId(long deliverySessionId) {
            this.deliverySessionId = deliverySessionId;
            return this;
        }

        public Builder action(ActionType action) {
            this.action = action;
            return this;
        }

        public Builder tmgiPool(String tmgiPool) {
            this.tmgiPool = tmgiPool;
            return this;
        }

        public Builder tmgi(String tmgi) {
            this.tmgi = tmgi;
            return this;
        }

        public Builder startTime(Long startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder stopTime(Long stopTime) {
            this.stopTime = stopTime;
            return this;
        }

        public Builder version(String version) {
            this.version = version;
            return this;
        }

        public DeliverySessionCreationType build() {
            DeliverySessionCreationType deliverySessionCreationType = new DeliverySessionCreationType();
            deliverySessionCreationType.setDeliverySessionId(deliverySessionId);
            deliverySessionCreationType.setAction(action);
            deliverySessionCreationType.setStartTime(startTime);
            deliverySessionCreationType.setStopTime(stopTime);
            deliverySessionCreationType.setVersion(version);
            deliverySessionCreationType.tmgi = this.tmgi;
            deliverySessionCreationType.tmgiPool = this.tmgiPool;
            return deliverySessionCreationType;
        }
    }
}
