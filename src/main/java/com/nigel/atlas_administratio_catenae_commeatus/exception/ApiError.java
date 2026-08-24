package com.nigel.atlas_administratio_catenae_commeatus.exception;

import java.time.LocalDateTime;

public record ApiError(
        LocalDateTime timestamp,
        int status,
        String error,
        String message,
        String path) {
}
