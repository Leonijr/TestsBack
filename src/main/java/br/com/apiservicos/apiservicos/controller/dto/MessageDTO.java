package br.com.apiservicos.apiservicos.controller.dto;

import lombok.Data;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.Serializable;
import java.net.URI;
import java.util.Date;

@Data
public class MessageDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    private void setValues(String message,String error,Integer status) {
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().build().toUri();

        this.status = status;
        this.timestamp = new Date().getTime();
        this.message = message;
        this.path = uri.getRawPath();
        this.error = error;
    }

    public MessageDTO(String message, String error, Integer status) {
        this.setValues(message, error, status);
    }

    public MessageDTO(String message, Integer status) {
        this.setValues(message, null, status);
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }
}
