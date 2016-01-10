package com.rhttp.lib.uri;

import com.rhttp.lib.Utils;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;

abstract class UriSegmentCollection {
    private final String mDelimiter;


    public UriSegmentCollection(String delimiter){
        mDelimiter = delimiter;
    }

    public void append(String segment){
        append(segment, false);
    }

    public void append(String segment, boolean isEncoded){
        if(isEncoded){
            try{
                segment = Utils.urlDecode(segment);
            }catch (UnsupportedEncodingException ex){
                throw new RuntimeException(ex);
            }

        }
        segment = Utils.stripTrailing(segment, mDelimiter);
        if(Utils.isNullOrEmpty(segment)){
            return;
        }
        for(String chunk: segment.split(mDelimiter)){
            if(Utils.isNullOrEmpty(chunk)){
                continue;
            }
            appendSegment(chunk);
        }
    }

    protected abstract void appendSegment(String rawSegment);

    protected abstract Iterator<UriSegment> getSegments();

    public boolean isEmpty(){
        return !getSegments().hasNext();
    }

    public String compile(StringBuilder builder, Map<String, ?> params) throws UnsupportedEncodingException{
        Iterator<UriSegment> it = getSegments();

        while (it.hasNext()){
            it.next().append(builder, params);
            if(it.hasNext()){
                builder.append(mDelimiter);
            }
        }
        return builder.length() == 0 ? null : builder.toString();
    }

    public abstract UriSegmentCollection cloneSelf();
}
