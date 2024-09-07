package com.coderhack.exchanges.user;

import com.coderhack.entities.Badge;

import java.util.LinkedHashSet;

public record UserResponse(
        String id,
        String userName,
        int score,
        LinkedHashSet<Badge> badges
) {
}
