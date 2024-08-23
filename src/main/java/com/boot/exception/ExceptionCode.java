package com.boot.exception;

import lombok.Getter;

@Getter
public enum ExceptionCode {
	UNABLE_TO_SEND_EMAIL("Unable to send email");

    private final String message;

    ExceptionCode(String message) {
        this.message = message;
    }
}