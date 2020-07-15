package com.mvvmrxjavatemp.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseResponse {

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessageEn() {
        return messageEn;
    }

    public void setMessageEn(String messageEn) {
        this.messageEn = messageEn;
    }

    @Expose
    @SerializedName("error")
    private Boolean status;

    @Expose
    @SerializedName("message")
    private String messageEn;


    public boolean isSuccess() {
        return status;
    }
}
