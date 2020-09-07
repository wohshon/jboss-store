package com.demo.store.entity.status;

import com.google.gson.annotations.SerializedName;

public enum PaymentStatus {
    @SerializedName("NEW")
    NEW("New"),
    @SerializedName("PAID")
    PAID("Paid"),
    @SerializedName("DUE")
    DUE("Due"),    
    @SerializedName("Cancelled")
    CANCELLED("Cancelled");
    
    public final String label;

    private PaymentStatus(String label) {
        this.label = label;
    }

}
