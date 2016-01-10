package com.rhttp.lib.uri;

import java.io.UnsupportedEncodingException;
import java.util.Map;

abstract class UriSegment {
    private static final String TOKEN_PARAM_START = "{", TOKEN_PARAM_END = "}";
    private final boolean mIsCompilable;
    protected final String mTokenName, mRaw;


    protected UriSegment(String rawStr){
        mIsCompilable = rawStr.contains(TOKEN_PARAM_START);
        mRaw = rawStr;
        if(mIsCompilable){
            if(!rawStr.contains(TOKEN_PARAM_END)){
                throw new IllegalArgumentException("malformed segment");
            }
            mTokenName = rawStr.substring(rawStr.indexOf(TOKEN_PARAM_START)+1, rawStr.indexOf(TOKEN_PARAM_END));
        }else{
            mTokenName = null;
        }
    }

    public String getTokenName(){
        return mTokenName;
    }

    public String getName(){
        return getTokenName();
    }

    public boolean isCompilable(){
        return mIsCompilable;
    }

    public abstract void append (StringBuilder out, Map<String, ?> params) throws UnsupportedEncodingException;
}
