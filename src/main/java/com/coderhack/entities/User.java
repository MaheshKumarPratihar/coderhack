package com.coderhack.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.LinkedHashSet;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(value = "users")
public class User {

    @Id
    private String id;

    private String userName;

    private int score = 0;

    private LinkedHashSet<Badge> badges;

}
