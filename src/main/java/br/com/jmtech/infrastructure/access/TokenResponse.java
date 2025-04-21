package br.com.jmtech.infrastructure.access;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TokenResponse {

    private String token;
}
