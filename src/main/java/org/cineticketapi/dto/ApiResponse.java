package org.cineticketapi.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ApiResponse<T> {
    private boolean success;
    private int statusCode;
    private String message;
    private T data;
}
