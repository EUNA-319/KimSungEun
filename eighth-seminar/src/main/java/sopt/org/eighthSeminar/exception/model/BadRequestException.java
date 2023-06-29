package sopt.org.eighthSeminar.exception.model;

import sopt.org.eighthSeminar.exception.Error;
public class BadRequestException extends SoptException {
    public BadRequestException(Error error, String message) {
        super(error, message);
    }
}
