package com.rhttp.lib.uri;

import com.rhttp.lib.Utils;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class UriQuery extends UriSegmentCollection{
    public static final String SEGMENT_DELIMITER = "&", KEY_VALUE_DELIMITER = "=";

    protected static class Segment extends UriSegment{

        private final String mName;


        public Segment(String rawStr) {
            super(rawStr);
            mName = rawStr.split(KEY_VALUE_DELIMITER)[0];
        }

        public Segment(String name, String value){
            super(value);
            mName = name;
        }

        @Override
        public void append(StringBuilder out, Map<String, ?> params) throws UnsupportedEncodingException {
            if(isCompilable()){
                String value = Utils.urlEncode(String.valueOf(params.get(getTokenName())));
                out.append(Utils.urlEncode(getName())).append(KEY_VALUE_DELIMITER).append( value );
            }else{
                out.append(mRaw);
            }

        }

        @Override
        public String getName(){
            return mName;
        }
    }

    private LinkedHashMap<String, UriSegment> mSegments;

    public UriQuery() {
        super(SEGMENT_DELIMITER);
    }

    @Override
    protected void appendSegment(String rawSegment) {
        appendSegment(new Segment(rawSegment));
    }


    protected void appendSegment(Segment segment){
        if(mSegments == null){
            mSegments = new LinkedHashMap<>();
        }
        mSegments.put(segment.getName(), segment);
    }

    @Override
    protected Iterator<UriSegment> getSegments() {
        if(mSegments == null){
            return Collections.emptyIterator();
        }
        return mSegments.values().iterator();
    }

    public UriQuery cloneSelf(){
        UriQuery instance = new UriQuery();
        if(mSegments != null){
            instance.mSegments = new LinkedHashMap<>();
            instance.mSegments.putAll(mSegments);
        }
        return instance;
    }
}
