package gov.iti.career.hub.controllers.register;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ResourceCreatedMessage implements Serializable {
    private Integer status;
    private LocalDateTime timestamp;
    private String message;
    public ResourceCreatedMessage(String message) {
        this.status = 201;
        this.timestamp = LocalDateTime.now();
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
