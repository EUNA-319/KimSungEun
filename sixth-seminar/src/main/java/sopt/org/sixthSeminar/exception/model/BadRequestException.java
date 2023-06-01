package sopt.org.sixthSeminar.exception.model;

import sopt.org.sixthSeminar.exception.Error;

public class BadRequestException extends SoptException {
    public BadRequestException(Error error, String message) {
        super(error, message);
    }
}
