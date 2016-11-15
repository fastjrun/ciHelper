package com.fastjrun.packet.req;

public class RequestHead {

    private String txType;

    private String methodName;

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    private String version;

    private String deviceId;

    private String reqTime;

    public String getTxType() {
        return txType;
    }

    public void setTxType(String txType) {
        this.txType = txType;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getReqTime() {
        return reqTime;
    }

    public void setReqTime(String reqTime) {
        this.reqTime = reqTime;
    }

    @Override
    public String toString() {
        return "RequestHead [txType=" + txType + ", methodName=" + methodName
                + ", version=" + version + ", deviceId=" + deviceId
                + ", reqTime=" + reqTime + "]";
    }

}
