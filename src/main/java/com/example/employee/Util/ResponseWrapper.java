package com.example.employee.Util;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseWrapper {

    private static final String defaultStatus = String.valueOf(HttpStatus.OK.value());
    private static final String defaultMessage = "Thành Công";

    @Builder.Default
    private Date timestamp = new Date();

    private String httpStatusCode;

    @Builder.Default
    private String messageCode = defaultMessage;

    private Object data;

    @Builder.Default
    private boolean success = true;

    private Object additionalInfo;

    public ResponseWrapper(Object data) {
        this(new Date(), defaultStatus, defaultMessage, data, true, null);
    }

    public ResponseWrapper(Object data, Object additionalInfo) {
        this(new Date(), defaultStatus, defaultMessage, data, true, additionalInfo);
    }

    public ResponseWrapper(String message, Object data) {
        this(new Date(), defaultStatus, message, data, true, null);
    }

    public ResponseWrapper(String message, Object data, Object additionalInfo) {
        this(new Date(), defaultStatus, message, data, true, additionalInfo);
    }

    public ResponseWrapper(String httpStatusCode, String errorMessage) {
        this(new Date(), httpStatusCode, errorMessage, null, false, null);
    }

}
