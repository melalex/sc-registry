package com.fpm.registry.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Urls {

    private static final String SIGN_IN_ERROR_MESSAGE = "errors.login.fail";

    public static final String ROOT = "/";
    public static final String INDEX = "/documents";
    public static final String LOGIN = "/signIn";
    public static final String LOGIN_FAIL = LOGIN + "?errors=" + SIGN_IN_ERROR_MESSAGE;
    public static final String REGISTER = "/signUp";
    public static final String CREATE_DOCUMENT = "/documents/create";

    public static final String LOGIN_MATCHER = "/signIn**";
    public static final String WEBJARS_MATCHER = "/webjars/**";
}
