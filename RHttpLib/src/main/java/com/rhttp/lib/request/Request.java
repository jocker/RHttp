package com.rhttp.lib.request;

import com.rhttp.lib.uri.RequestUri;

import java.lang.reflect.Type;

public class Request<T> {
    public static class Builder{
        private RequestVerb mVerb;
        private RequestUri mUri;
        private Type mTypeOfT;

    }
}
