package com.elfak.whserver.web;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class WebConstant {

    public static final String USER_BASE_URL = "/api/v1/users";
    public static final String WH_BASE_URL = "/api/v1/wh";

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class WebHook {

        public static final String CREATE = WH_BASE_URL + "/create";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class User {

        public static final String CREATE = USER_BASE_URL + "/register";
    }
}

