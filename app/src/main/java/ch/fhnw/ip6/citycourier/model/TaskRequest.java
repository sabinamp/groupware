package ch.fhnw.ip6.citycourier.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.Objects;

public class TaskRequest {
    @JsonProperty("orderId")
    private String orderId = null;

    @JsonProperty("assigneeId")
    private String assigneeId = null;

    @JsonProperty("addressLine")
    private String addressLine = null;

    @JsonProperty("deliveryType")
    private DeliveryType deliveryType = null;

    @JsonProperty("taskType")
    private TaskType taskType = null;

    @JsonProperty("dueOn")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm", locale = "de")
    private LocalDateTime dueOn = null;

    @JsonProperty("shift")
    private ShiftType shift = null;

    @JsonProperty("sentWhen")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm", locale = "de")
    private LocalDateTime  sentWhen = null;


    public TaskRequest dueOn(LocalDateTime dueOn) {
        this.dueOn = dueOn;
        return this;
    }

    public TaskRequest sentWhen(LocalDateTime sentWhen) {
        this.sentWhen = sentWhen;
        return this;
    }

    public TaskRequest shift(ShiftType shift) {
        this.shift = shift;
        return this;
    }

    public TaskRequest deliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
        return this;
    }

    public TaskRequest taskType(TaskType taskType) {
        this.taskType = taskType;
        return this;
    }

    public TaskRequest orderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public TaskRequest assigneeId(String assigneeId) {
        this.assigneeId = assigneeId;
        return this;
    }

    public TaskRequest addressLine(String addressLine) {
        this.addressLine = addressLine;
        return this;
    }
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(String assigneeId) {
        this.assigneeId = assigneeId;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm", locale = "de")
    public LocalDateTime getDueOn() {
        return dueOn;
    }

    public void setDueOn(LocalDateTime dueOn) {
        this.dueOn = dueOn;
    }

    public ShiftType getShift() {
        return shift;
    }

    public void setShift(ShiftType shift) {
        this.shift = shift;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm", locale = "de")
    public LocalDateTime getSentWhen() {
        return sentWhen;
    }

    public void setSentWhen(LocalDateTime sentWhen) {
        this.sentWhen = sentWhen;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    @JsonProperty("confirmed")
    private boolean confirmed = false;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TaskRequest task = (TaskRequest) o;
        return Objects.equals(this.orderId, task.orderId) &&
                Objects.equals(this.assigneeId, task.assigneeId)&&
                Objects.equals(this.addressLine, task.addressLine)&&
                Objects.equals(this.deliveryType, task.deliveryType) &&
                Objects.equals(this.sentWhen, task.sentWhen) &&
                Objects.equals(this.taskType, task.taskType) &&
                Objects.equals(this.dueOn, task.dueOn) &&
                Objects.equals(this.confirmed, task.confirmed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, assigneeId, addressLine,sentWhen, deliveryType, taskType, dueOn,
                confirmed);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TaskRequest {\n");
        sb.append("    orderId: ").append(toIndentedString(orderId)).append("\n");
        sb.append("    assigneeId: ").append(toIndentedString(assigneeId)).append("\n");
        sb.append("    addressLine: ").append(toIndentedString(addressLine)).append("\n");
        sb.append("    sentWhen: ").append(toIndentedString(sentWhen)).append("\n");
        sb.append("    deliveryType: ").append(toIndentedString(deliveryType)).append("\n");
        sb.append("    taskType: ").append(toIndentedString(taskType)).append("\n");
        sb.append("    dueOn: ").append(toIndentedString(dueOn)).append("\n");
        sb.append("    confirmed: ").append(toIndentedString(confirmed)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }


}