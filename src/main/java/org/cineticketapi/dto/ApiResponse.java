package org.cineticketapi.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ApiResponse<T> {
    boolean success;
    int statusCode;
    String message;
    T data;
}
