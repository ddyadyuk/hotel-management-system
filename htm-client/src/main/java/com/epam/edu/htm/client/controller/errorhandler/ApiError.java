package com.epam.edu.htm.client.controller.errorhandler;

import org.springframework.http.HttpStatus;

/**
 * The type Api error.
 */
public class ApiError {

    private HttpStatus status;
    private String message;

    /**
     * Instantiates a new Api error.
     *
     * @param status  the status
     * @param message the message
     */
    ApiError(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public HttpStatus getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ApiError{"
                + "status=" + status
                + ", message='" + message + '\''
                + '}';
    }
}