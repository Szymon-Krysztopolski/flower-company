package com.flowercompany.gateway.exception;

public class OrderException extends RuntimeException {
    public OrderException(Throwable cause) {
        super("Error with your order!", cause);
    }
}
