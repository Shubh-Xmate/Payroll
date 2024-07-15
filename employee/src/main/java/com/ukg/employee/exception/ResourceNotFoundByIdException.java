package com.ukg.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundByIdException extends RuntimeException{
    public ResourceNotFoundByIdException(String entity, String fieldName, Long fieldValue){
        super(String.format("In %s , %s is not found for value %s", entity, fieldName, fieldValue));
    }

}
