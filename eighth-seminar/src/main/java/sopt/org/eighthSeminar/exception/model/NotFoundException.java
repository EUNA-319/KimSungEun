package sopt.org.eighthSeminar.exception.model;

import sopt.org.eighthSeminar.exception.Error;
public class NotFoundException extends SoptException {
    public NotFoundException(Error error, String message) {
        super(error, message);
    }
}
