package ch.fhnw.ip6.citycourier.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.Objects;

public class TaskRequest {
    @JsonProperty("taskId")
    private String taskId = null;

    @JsonProperty("orderId")
    private String orderId = null;

    @JsonProperty("assigneeId")
    private String assigneeId = null;

    @JsonProperty("deliveryType")
    private DeliveryType deliveryType = null;

    @JsonProperty("taskType")
    private TaskType taskType = null;

    @JsonProperty("dueOn")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm", locale = "de")
    private LocalDateTime  dueOn = null;

    @JsonProperty("sentWhen")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm", locale = "de")
    private LocalDateTime  sentWhen = null;

    @JsonProperty("confirmed")
    private RequestReply confirmed = null;

    @JsonProperty("done")
    private boolean done = false;

    @JsonProperty("completedWhen")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm", locale = "de")
    private LocalDateTime completedWhen = null;


    @JsonProperty("outcome")
    private OrderStatus outcome = null;

    public TaskRequest taskId(String taskId) {
        this.taskId = taskId;
        return this;
    }

    public TaskRequest dueOn(LocalDateTime dueOn) {
        this.dueOn = dueOn;
        return this;
    }

    public TaskRequest sentWhen(LocalDateTime sentWhen) {
        this.sentWhen = sentWhen;
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

    public TaskRequest confirmed(RequestReply accepted){
        this.confirmed = accepted;
        return this;
    }

    public TaskRequest done(boolean done){
        this.done = done;
        return this;
    }

    public TaskRequest completedWhen(LocalDateTime completedWhen) {
        this.completedWhen = completedWhen;
        return this;
    }
    public TaskRequest taskType(OrderStatus outcome) {
        this.outcome = outcome;
        return this;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
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


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm", locale = "de")
    public LocalDateTime getSentWhen() {
        return sentWhen;
    }

    public void setSentWhen(LocalDateTime sentWhen) {
        this.sentWhen = sentWhen;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm", locale = "de")
    public LocalDateTime getCompletedWhen() {
        return completedWhen;
    }

    public void setCompletedWhen(LocalDateTime completedWhen) {
        this.completedWhen = completedWhen;
    }


    public RequestReply getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(RequestReply confirmed) {
        this.confirmed = confirmed;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public OrderStatus getOutcome() {
        return outcome;
    }

    public void setOutcome(OrderStatus outcome) {
        this.outcome = outcome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TaskRequest task = (TaskRequest) o;
        return  Objects.equals(this.taskId, task.taskId) &&
                Objects.equals(this.orderId, task.orderId) &&
                Objects.equals(this.assigneeId, task.assigneeId)&&
                Objects.equals(this.deliveryType, task.deliveryType) &&
                Objects.equals(this.sentWhen, task.sentWhen) &&
                Objects.equals(this.taskType, task.taskType) &&
                Objects.equals(this.dueOn, task.dueOn) &&
                Objects.equals(this.confirmed, task.confirmed)&&
                Objects.equals(this.done, task.done) &&
                Objects.equals(this.completedWhen, task.completedWhen) &&
                Objects.equals(this.outcome, task.outcome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, orderId, assigneeId, sentWhen, deliveryType, taskType, dueOn,
                confirmed, done, completedWhen, outcome);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TaskRequest {\n");
        sb.append("    taskId: ").append(toIndentedString(taskId)).append("\n");
        sb.append("    orderId: ").append(toIndentedString(orderId)).append("\n");
        sb.append("    assigneeId: ").append(toIndentedString(assigneeId)).append("\n");
        sb.append("    sentWhen: ").append(toIndentedString(sentWhen)).append("\n");
        sb.append("    deliveryType: ").append(toIndentedString(deliveryType)).append("\n");
        sb.append("    taskType: ").append(toIndentedString(taskType)).append("\n");
        sb.append("    dueOn: ").append(toIndentedString(dueOn)).append("\n");
        sb.append("    confirmed: ").append(toIndentedString(confirmed)).append("\n");
        sb.append("    done: ").append(toIndentedString(done)).append("\n");
        sb.append("    completedWhen: ").append(toIndentedString(completedWhen)).append("\n");
        sb.append("    outcome: ").append(toIndentedString(outcome)).append("\n");
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
