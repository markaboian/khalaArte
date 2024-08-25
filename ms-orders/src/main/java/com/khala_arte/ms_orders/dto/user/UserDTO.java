package com.khala_arte.ms_orders.dto.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private String lastName;
    private String email;
}
