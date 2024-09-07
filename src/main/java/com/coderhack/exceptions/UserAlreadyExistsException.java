package com.coderhack.exceptions;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Getter
@Setter
public class UserAlreadyExistsException extends RuntimeException {
    private final String msg;
}
