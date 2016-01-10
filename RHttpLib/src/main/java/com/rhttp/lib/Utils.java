package com.rhttp.lib;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class Utils {

    private static final String EMPTY_STRING = "";

    public static boolean isNullOrEmpty(String text){
        return text == null || EMPTY_STRING.equals(text);
    }

    public static String stripTrailing(String source, String toStrip){
        if(!isNullOrEmpty(source) && !isNullOrEmpty(toStrip)){
            int len = toStrip.length();
            if(source.startsWith(toStrip)){
                source = source.substring(len);
            }
            if(source.endsWith(toStrip)){
                source = source.substring(0, source.length() - len);
            }
        }
        return source;
    }

    public static String urlEncode(String value) throws UnsupportedEncodingException {
        String encodedValue = URLEncoder.encode(value, "UTF-8");
        return encodedValue.replace("+", "%20");
    }

    public static String urlDecode(String value) throws UnsupportedEncodingException{
        return URLDecoder.decode(value, "UTF-8");
    }
}
