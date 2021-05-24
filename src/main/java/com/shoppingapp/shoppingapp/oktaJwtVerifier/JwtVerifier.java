package com.shoppingapp.shoppingapp.oktaJwtVerifier;

import com.okta.jwt.*;
import java.time.Duration;

public class JwtVerifier {
    public AccessTokenVerifier accessTokenVerifier;
    public IdTokenVerifier idTokenVerifier;
    public String stripBearer(String authorization) {
        return authorization.replace("Bearer ", "");
    }

    @SuppressWarnings("SpellCheckingInspection")
    public JwtVerifier() {
        accessTokenVerifier = JwtVerifiers.accessTokenVerifierBuilder()
                .setIssuer(System.getenv("ISSUER"))
                .setAudience("api://default")                   // defaults to 'api://default'
                .setConnectionTimeout(Duration.ofSeconds(1))    // defaults to 1s
                .setRetryMaxAttempts(2)                     // defaults to 2
                .setRetryMaxElapsed(Duration.ofSeconds(10)) // defaults to 10s
                .build();
        idTokenVerifier = JwtVerifiers.idTokenVerifierBuilder()
                .setIssuer(System.getenv("ISSUER"))
                .setClientId(System.getenv("CLIENT_ID"))
                .setConnectionTimeout(Duration.ofSeconds(1))    // defaults to 1s
                .setRetryMaxAttempts(2)                     // defaults to 2
                .setRetryMaxElapsed(Duration.ofSeconds(10)) // defaults to 10s
                .build();
    }
}

//AccessTokenVerifier jwtVerifier = JwtVerifiers.accessTokenVerifierBuilder()
//        .setIssuer("")
//        .setAudience("")                   // defaults to 'api://default'
//        .setConnectionTimeout(Duration.ofSeconds(1))    // defaults to 1s
//        .setRetryMaxAttempts(2)                     // defaults to 2
//        .setRetryMaxElapsed(Duration.ofSeconds(10)) // defaults to 10s
//        .build();