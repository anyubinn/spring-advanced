package org.example.expert.domain.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignupResponse {

    private final String bearerToken;

    public static SignupResponse of(String bearerToken) {
        return new SignupResponse(bearerToken);
    }
}
