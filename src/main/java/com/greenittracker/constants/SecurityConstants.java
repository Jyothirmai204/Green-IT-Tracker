package com.greenittracker.constants;

public final class SecurityConstants {

    private SecurityConstants() {
    }

    public static final String AUTH_HEADER =
            "Authorization";

    public static final String TOKEN_PREFIX =
            "Bearer ";

    public static final long JWT_EXPIRATION =
            86400000;
}