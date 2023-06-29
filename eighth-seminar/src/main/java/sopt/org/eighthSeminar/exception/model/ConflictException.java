package sopt.org.eighthSeminar.exception.model;

import sopt.org.eighthSeminar.exception.Error;
public class ConflictException extends SoptException {
    public ConflictException(Error error, String message) {
        super(error, message);
    }
}
