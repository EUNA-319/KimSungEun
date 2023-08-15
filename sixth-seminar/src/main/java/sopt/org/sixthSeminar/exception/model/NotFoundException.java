package sopt.org.sixthSeminar.exception.model;

import sopt.org.sixthSeminar.exception.Error;

public class NotFoundException extends SoptException{
    public NotFoundException(Error error, String message) {
        super(error, message);
    }
}
