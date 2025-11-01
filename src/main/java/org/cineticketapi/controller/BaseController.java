package org.cineticketapi.controller;

import org.cineticketapi.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseController {
    private ApiResponse<?> apiResponse;

    public <T> ApiResponse<?> okApiResponse(T dataContent) {
        apiResponse = ApiResponse.<T>builder()
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .data(dataContent)
                .build();

        return apiResponse;
    }

    public <T> ApiResponse<?> createdApiResponse(T dataContent) {
        apiResponse = ApiResponse.<T>builder()
                .success(true)
                .statusCode(HttpStatus.CREATED.value())
                .data(dataContent)
                .build();

        return apiResponse;
    }

    public ResponseEntity<ApiResponse<?>> apiExceptionResponse(HttpStatus httpStatus, String message, Object dataContent) {
        switch (httpStatus) {
            case BAD_REQUEST:
                return ResponseEntity.badRequest().body(this.badReqApiResponse(message, dataContent));
            case INTERNAL_SERVER_ERROR:
                return ResponseEntity.internalServerError().body(this.errorApiResponse(message, dataContent));
            default:
                return ResponseEntity.badRequest().body(this.badReqApiResponse(message, dataContent));
        }
    }

    public ApiResponse<?> badReqApiResponse(String message, Object dataContent) {
        apiResponse = ApiResponse.<Object>builder()
                .success(false)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(message)
                .data(dataContent)
                .build();

        return apiResponse;
    }

    public ApiResponse<?> errorApiResponse(String message, Object dataContent) {
        apiResponse = ApiResponse.<Object>builder()
                .success(false)
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(message)
                .data(dataContent)
                .build();

        return apiResponse;
    }
}
