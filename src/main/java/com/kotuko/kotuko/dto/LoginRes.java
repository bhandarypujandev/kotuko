package com.kotuko.kotuko.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class LoginRes {
    private String token;
}
