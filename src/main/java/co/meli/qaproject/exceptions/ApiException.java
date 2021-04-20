package co.meli.qaproject.exceptions;

import org.springframework.http.HttpStatus;

public class ApiException extends Exception{
    HttpStatus httpStatus;
    String msg;

    public ApiException(HttpStatus httpStatus, String msg) {
        super(msg);
        this.httpStatus = httpStatus;
        this.msg = msg;
    }
}
