package com.coderhack.exceptions;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Getter
@Setter
public class UserNotFoundException extends RuntimeException {
    private final String msg;
}
