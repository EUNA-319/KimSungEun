package sopt.org.sixthSeminar.exception.model;

import sopt.org.sixthSeminar.exception.Error;

public class UnauthorizedException extends SoptException{
    public UnauthorizedException(Error error, String message) {
        super(error, message);
    }
}
