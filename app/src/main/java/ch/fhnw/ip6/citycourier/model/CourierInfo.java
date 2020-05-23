package ch.fhnw.ip6.citycourier.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

public class CourierInfo {

    @JsonProperty("courierName")
    private String courierName = null;

    @JsonProperty("deviceName")
    private String deviceName = null;

    @JsonProperty("conn")
    private String conn = null;

    @JsonProperty("status")
    private String status = null;

    @JsonProperty("location")
    private Location location = null;

    @JsonProperty("avatarImage")
    private String avatarImage = null;

    @JsonProperty("contactInfos")
    private List<ContactInfo> contactInfos = null;


    @JsonProperty("assignedOrders")
    private Set<String> assignedOrders = null;

    public CourierInfo assignedOrders(Set<String> assignedOrders) {
        this.assignedOrders = assignedOrders;
        return this;
    }

    public CourierInfo avatarImage(String avatarImage) {
        this.avatarImage= avatarImage;
        return this;
    }

    public CourierInfo courierName(String courierName) {
        this.courierName = courierName;
        return this;
    }

    public CourierInfo status(String status) {
        this.status= status;
        return this;
    }

    public CourierInfo conn(String conn) {
        this.conn= conn;
        return this;
    }

    public CourierInfo location(Location location) {
        this.location= location;
        return this;
    }
    public CourierInfo contactInfos(List<ContactInfo> contactInfos) {
        this.contactInfos = contactInfos;
        return this;
    }

    public CourierInfo addContactInfosItem(ContactInfo contactInfosItem) {
        if (this.contactInfos == null) {
            this.contactInfos = new ArrayList<>();
        }
        this.contactInfos.add(contactInfosItem);
        return this;
    }

    public String getCourierName() {
        return courierName;
    }

    public void setCourierName(String courierName) {
        this.courierName = courierName;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getConn() {
        return conn;
    }

    public void setConn(String conn) {
        this.conn = conn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getAvatarImage() {
        return avatarImage;
    }

    public void setAvatarImage(String avatarImage) {
        this.avatarImage = avatarImage;
    }

    public List<ContactInfo> getContactInfos() {
        return contactInfos;
    }

    public void setContactInfos(List<ContactInfo> contactInfos) {
        this.contactInfos = contactInfos;
    }

    public Set<String> getAssignedOrders() {
        return assignedOrders;
    }

    public void setAssignedOrders(Set<String> assignedOrders) {
        this.assignedOrders = assignedOrders;
    }

    public CourierInfo addAssignedOrder(String assignedOrder) {
        if (this.assignedOrders == null) {
            this.assignedOrders = new TreeSet<>();
        }
        this.assignedOrders.add(assignedOrder);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourierInfo courierInfo = (CourierInfo) o;
        return Objects.equals(this.courierName, courierInfo.courierName) &&
                Objects.equals(this.deviceName, courierInfo.deviceName) &&
                Objects.equals(this.conn, courierInfo.conn) &&
                Objects.equals(this.status, courierInfo.status) &&
                Objects.equals(this.avatarImage, courierInfo.avatarImage) &&
                Objects.equals(this.contactInfos, courierInfo.contactInfos) &&
                Objects.equals(this.assignedOrders, courierInfo.assignedOrders) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(courierName, deviceName, conn, status, avatarImage, contactInfos,assignedOrders);
    }

    @Override
    public String toString() {
        return "CourierInfo{" +
                "courierName='" + courierName + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", conn='" + conn + '\'' +
                ", status='" + status + '\'' +
                ", location=" + location + '\'' +
                ", avatarImage='" + avatarImage + '\'' +
                ", assignedOrders='" + assignedOrders + '\'' +
                ", contactInfos=" + contactInfos +
                '}';
    }


}
