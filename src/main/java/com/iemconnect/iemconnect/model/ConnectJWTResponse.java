package com.iemconnect.iemconnect.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ConnectJWTResponse {

    private String jwtToken;
    private String userName;

}
