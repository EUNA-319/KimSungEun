package sopt.org.eighthSeminar.exception.model;

import sopt.org.eighthSeminar.exception.Error;

public class UnauthorizedException extends SoptException {
    public UnauthorizedException(Error error, String message) {
        super(error, message);
    }
}
