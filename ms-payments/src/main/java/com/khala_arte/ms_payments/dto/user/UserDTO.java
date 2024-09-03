package com.khala_arte.ms_payments.dto.user;

import lombok.*;

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
