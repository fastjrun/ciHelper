package com.fastjrun.packet;

import java.io.Serializable;

public class BaseCoreRequestHead implements Serializable {
    
    private static final long serialVersionUID = 8910701273528680096L;

    // 接口编码
    private String txCode;

    // 交易流水号
    private String transCode;

    // 调用服务方
    private String appCode;

    // 交易时间
    private String transTime;

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getTxCode() {
        return txCode;
    }

    public void setTxCode(String txCode) {
        this.txCode = txCode;
    }

    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }

    public String getTransTime() {
        return transTime;
    }

    public void setTransTime(String transTime) {
        this.transTime = transTime;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(("BaseCoreRequestHead"+" ["));
        sb.append("txCode");
        sb.append("=");
        sb.append(this.txCode);
        sb.append(",");
        sb.append("transCode");
        sb.append("=");
        sb.append(this.transCode);
        sb.append(",");
        sb.append("transTime");
        sb.append("=");
        sb.append(this.transTime);
        sb.append(",");
        sb.append("appCode");
        sb.append("=");
        sb.append(this.appCode);
        sb.append("]");
        return sb.toString();
    }

}
