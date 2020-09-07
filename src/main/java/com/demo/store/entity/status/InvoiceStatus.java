package com.demo.store.entity.status;

import com.google.gson.annotations.SerializedName;

public enum InvoiceStatus {
    @SerializedName("NEW")
    NEW("New"),
    @SerializedName("ISSUED")
    PAID("ISSUED"),
    @SerializedName("Cancelled")
    CANCELLED("Cancelled");

    public final String label;

    private InvoiceStatus(String label) {
        this.label = label;
    }

}
