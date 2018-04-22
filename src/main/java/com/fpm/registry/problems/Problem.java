package com.fpm.registry.problems;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@Value
@Builder
public class Problem {

    private LocalDateTime timestamp;
    private String type;
    private int status;
    private String detail;
    private String message;
    private List<NestedError> errors;

    @Value
    @Builder
    public static class NestedError {

        private String field;
        private Object rejected;
        private String message;
    }
}
