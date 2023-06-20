package sopt.org.seventhSeminar.exception.model;

import sopt.org.seventhSeminar.exception.Error;

public class ConflictException extends SoptException {
    public ConflictException(Error error, String message) {
        super(error, message);
    }
}
