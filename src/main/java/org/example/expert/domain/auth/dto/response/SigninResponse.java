package org.example.expert.domain.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SigninResponse {

    private final String bearerToken;

    public static SigninResponse of(String bearerToken) {
        return new SigninResponse(bearerToken);
    }
}
