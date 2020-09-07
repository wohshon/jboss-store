package com.demo.store.entity.status;

import com.google.gson.annotations.SerializedName;

public enum DeliveryStatus {
    @SerializedName("NEW")
    NEW("New"),
    @SerializedName("DELIVERED")
    DELIVERED("Delivered"),
    @SerializedName("Cancelled")
    CANCELLED("Cancelled");
    
    public final String label;

    private DeliveryStatus(String label) {
        this.label = label;
    }

}
