package com.rhttp.lib.request;

public enum RequestVerb {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE"),
    PATCH("PATCH"),
    HEAD("HEAD"),
    OPTIONS("OPTIONS"),
    TRACE("TRACE");

    public final String name;

    RequestVerb(String name){
        this.name = name;
    }
}
