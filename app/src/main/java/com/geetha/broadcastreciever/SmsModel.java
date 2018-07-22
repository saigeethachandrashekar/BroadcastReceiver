package com.geetha.broadcastreciever;

/**
 * Created by Rdl on 19-07-2018.
 */

class SmsModel {
    private String sender;
    private String msgDate;
    private String msg;

    public SmsModel(String sender, String msgDate, String msg) {
        this.sender = sender;
        this.msgDate = msgDate;
        this.msg = msg;
    }

    public SmsModel() {

    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsgDate() {
        return msgDate;
    }

    public void setMsgDate(String msgDate) {
        this.msgDate = msgDate;
    }

    @Override
    public String toString() {
        return "SmsModel{" +
                "sender='" + sender + '\'' +
                ", msgDate='" + msgDate + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
