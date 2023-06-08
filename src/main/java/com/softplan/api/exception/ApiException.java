package com.softplan.api.exception;

public class ApiException extends Exception{

    public ApiException(String message) {super(message);}

    public ApiException(Throwable t) {super(t);}
}
