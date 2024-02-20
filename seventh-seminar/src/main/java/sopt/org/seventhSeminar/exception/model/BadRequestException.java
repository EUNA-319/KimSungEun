package sopt.org.seventhSeminar.exception.model;

import sopt.org.seventhSeminar.exception.Error;
public class BadRequestException extends SoptException {
    public BadRequestException(Error error, String message) {
        super(error, message);
    }
}
