package sopt.org.seventhSeminar.exception.model;

import sopt.org.seventhSeminar.exception.Error;
public class UnauthorizedException extends SoptException{
    public UnauthorizedException(Error error, String message) {
        super(error, message);
    }
}
