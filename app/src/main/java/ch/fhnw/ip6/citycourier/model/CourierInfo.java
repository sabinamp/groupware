package ch.fhnw.ip6.citycourier.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class CourierInfo {

    @JsonProperty("courierName")
    private String courierName = null;

    @JsonProperty("deviceName")
    private String deviceName = null;

    @JsonProperty("conn")
    private Conn conn = null;

    @JsonProperty("status")
    private CourierStatus status = null;

/*    @JsonProperty("location")
    private Location location = null;*/

    @JsonProperty("avatarImage")
    private AvatarImage avatarImage = null;

    @JsonProperty("contactInfo")
    private ContactInfo contactInfo = null;


    @JsonProperty("assignedOrders")
    private Set<String> assignedOrders = null;

    public CourierInfo assignedOrders(Set<String> assignedOrders) {
        this.assignedOrders = assignedOrders;
        return this;
    }

    public CourierInfo avatarImage(AvatarImage avatarImage) {
        this.avatarImage= avatarImage;
        return this;
    }

    public CourierInfo courierName(String courierName) {
        this.courierName = courierName;
        return this;
    }

    public CourierInfo status(CourierStatus status) {
        this.status= status;
        return this;
    }

    public CourierInfo conn(Conn conn) {
        this.conn= conn;
        return this;
    }

 /*   public CourierInfo location(Location location) {
        this.location= location;
        return this;
    }*/
    public CourierInfo contactInfos(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
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

    public Conn getConn() {
        return conn;
    }

    public void setConn(Conn conn) {
        this.conn = conn;
    }

    public CourierStatus getStatus() {
        return status;
    }

    public void setStatus(CourierStatus status) {
        this.status = status;
    }

/*
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
*/

    public AvatarImage getAvatarImage() {
        return avatarImage;
    }

    public void setAvatarImage(AvatarImage avatarImage) {
        this.avatarImage = avatarImage;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
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
                Objects.equals(this.contactInfo, courierInfo.contactInfo) &&
                Objects.equals(this.assignedOrders, courierInfo.assignedOrders) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(courierName, deviceName, conn, status, avatarImage, contactInfo,assignedOrders);
    }

    @Override
    public String toString() {
        return "CourierInfo{" +
                "courierName='" + courierName + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", conn='" + conn + '\'' +
                ", status='" + status + '\'' +
                ", avatarImage='" + avatarImage + '\'' +
                ", assignedOrders='" + assignedOrders + '\'' +
                ", contactInfo=" + contactInfo +
                '}';
    }


}
