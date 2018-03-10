package net.fina.scrapper.exception;

public class ConnectionFailureException extends Exception {
    public ConnectionFailureException(String message, Throwable cause) {
        super(message, cause);
    }
}
