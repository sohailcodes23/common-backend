package com.example.commonBackend.exceptions;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResourceNotFoundException extends RuntimeException {

    List<String> errors = new ArrayList<>();

    public ResourceNotFoundException(String exception) {
        super(exception);
        this.errors.add(exception);
    }
}
