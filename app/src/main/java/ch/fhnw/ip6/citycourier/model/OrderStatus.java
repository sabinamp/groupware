package ch.fhnw.ip6.citycourier.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
/**
 * Gets or Sets OrderStatus
 */
public enum OrderStatus {

    PENDING("New Order"),

    PARCEL_COLLECTED("Parcel Collected"),

    IN_PROGRESS("In Progress"),

    CANCELED("Canceled"),

    COMPLETED("Successful Delivery"),

    RETRY("Unsuccessful First Delivery Attempt. Rescheduled"),

    FAILED("Failed Delivery");

    private String value;

    OrderStatus(String value) {
        this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static OrderStatus fromValue(String text) {
        for (OrderStatus b : OrderStatus.values()) {
            if (String.valueOf(b.value).equals(text)) {
                return b;
            }
        }
        return null;
    }
}