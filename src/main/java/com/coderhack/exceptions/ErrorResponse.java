package com.coderhack.exceptions;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {

}
