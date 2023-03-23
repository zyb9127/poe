package com.poe.project.poe_project.java.config;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * AttributeCheckErrorException
 *
 * @author Breath
 * @date 2020/3/19
 */
@Data
@JsonIgnoreProperties({"cause", "stackTrace", "message", "localizedMessage", "suppressed"})
public class AttributeCheckErrorException extends RuntimeException {
    private String attrName;
    private String reason;
    private String modelId;
    private String ciName;
    private String ciId;
    private boolean flag;

    public AttributeCheckErrorException() {
    }

    public AttributeCheckErrorException(String attrName, String reason) {
        this.attrName = attrName;
        this.reason = reason;
    }

    public AttributeCheckErrorException(String attrName, String reason, String modelId) {
        this.attrName = attrName;
        this.reason = reason;
        this.modelId = modelId;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
