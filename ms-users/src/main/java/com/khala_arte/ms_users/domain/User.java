package com.khala_arte.ms_users.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String password;
}
