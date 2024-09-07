package com.coderhack.exchanges.user;

import jakarta.validation.constraints.NotNull;

public record UserRequest(
        @NotNull(message = "User Id is required")
        String id,
        @NotNull(message = "User name is required")
        String userName,
        int score
) {
        public UserRequest(String id, String userName){
                this(id, userName, 0);
        }
}
