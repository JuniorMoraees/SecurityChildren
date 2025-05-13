package br.com.jmtech.application.dto.Auth;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RefreshRequest {

    private String refreshToken;

}
