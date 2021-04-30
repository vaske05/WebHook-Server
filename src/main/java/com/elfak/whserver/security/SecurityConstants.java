package com.elfak.whserver.security;

import com.elfak.whserver.web.WebConstant;

public class SecurityConstants {
    public static final String SIGN_UP_URLS = WebConstant.USER_BASE_URL + "/**";
    //    public static final String H2_URL = "/h2-console/**";
    public static final String SECRET = "SecretKeyToGenJwts";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    public static final int EXPIRATION_TIME_IN_HOURS = 0;
    public static final int EXPIRATION_TIME_IN_MINUTES = 15;
    public static final int EXPIRATION_TIME_IN_SECONDS = 2;


    public static final String[] PUBLIC_MATCHERS = {
            /*"/webjars/**",
            "/css/**",
            "/js/**",
            "/images/**",
            "/",
            "/about/**",
            "/contact/**",
            "/error/**",
            "/console/**",
            "/signup",*/

            /*NEW*/
            "/",
            "favicon.ico",
            "/**/*.png",
            "/**/*.gif",
            "/**/*.svg",
            "/**/*.jpg",
            "/**/*.html",
            "/**/*.js"
    };
}
