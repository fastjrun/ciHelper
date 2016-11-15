package com.fastjrun.packet;

import java.io.Serializable;

public class BaseRestRequestHead implements Serializable {

    private static final long serialVersionUID = -4242620759722491454L;

    private String deviceId;

    private long txTime;

    // appId
    private String appKey;

    // 版本号
    private String appVersion;

    public long getTxTime() {
        return txTime;
    }

    public void setTxTime(long txTime) {
        this.txTime = txTime;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(("BaseRestRequestHead" + " ["));
        sb.append("appKey");
        sb.append("=");
        sb.append(this.appKey);
        sb.append(",");
        sb.append("appVersion");
        sb.append("=");
        sb.append(this.appVersion);
        sb.append(",");
        sb.append("deviceId");
        sb.append("=");
        sb.append(this.deviceId);
        sb.append(",");
        sb.append("txTime");
        sb.append("=");
        sb.append(this.txTime);
        sb.append("]");
        return sb.toString();
    }
}
