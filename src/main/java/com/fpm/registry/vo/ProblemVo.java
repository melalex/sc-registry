package com.fpm.registry.vo;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@Value
@Builder
public class ProblemVo {

    private LocalDateTime timestamp;
    private String type;
    private int status;
    private String detail;
    private List<NestedErrorVo> errors;

    @Value
    @Builder
    public static class NestedErrorVo {

        private String field;
        private Object rejected;
        private String message;
    }
}
