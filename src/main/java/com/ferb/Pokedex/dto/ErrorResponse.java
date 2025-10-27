package com.ferb.Pokedex.dto;

import com.ferb.Pokedex.ErrorType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

@Builder
@AllArgsConstructor
@ToString
public class ErrorResponse<T> {
    public ErrorType errorType;
    public T message;

}
