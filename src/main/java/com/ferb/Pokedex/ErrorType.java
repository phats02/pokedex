package com.ferb.Pokedex;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorType {
    RESOURCE_NOT_FOUND("Resource not found"),
    INVALID_REQUEST("Invalid request"),
    UNEXPECTED_ERROR("Unexpected error");

    private final String description;
    ErrorType(String description){
        this.description= description;
    }

    @JsonValue
    public String toString() {
        return this.description;
    }

}
