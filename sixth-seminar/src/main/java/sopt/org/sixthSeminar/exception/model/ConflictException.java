package sopt.org.sixthSeminar.exception.model;

import sopt.org.sixthSeminar.exception.Error;
public class ConflictException extends SoptException {
    public ConflictException(Error error, String message) {
        super(error, message);
    }
}
