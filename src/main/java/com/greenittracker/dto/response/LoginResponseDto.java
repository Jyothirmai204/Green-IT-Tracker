package com.greenittracker.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponseDto {

    private Long userId;

    private String fullName;

    private String email;

    private String token;
}