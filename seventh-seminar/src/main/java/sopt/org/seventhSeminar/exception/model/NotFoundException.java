package sopt.org.seventhSeminar.exception.model;

import sopt.org.seventhSeminar.exception.Error;

public class NotFoundException extends SoptException{
    public NotFoundException(Error error, String message) {
        super(error, message);
    }
}
