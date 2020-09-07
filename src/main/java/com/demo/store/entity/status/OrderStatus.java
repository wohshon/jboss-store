package com.demo.store.entity.status;

import com.google.gson.annotations.SerializedName;

public enum OrderStatus {
    @SerializedName("NEW")
    NEW("New"),
    @SerializedName("PAID")
    PAID("Paid"),
    @SerializedName("DUE")
    DUE("Due"),
    @SerializedName("Cancelled")
    CANCELLED("Cancelled");
    public final String label;

    private OrderStatus(String label) {
        this.label = label;
    }

}
