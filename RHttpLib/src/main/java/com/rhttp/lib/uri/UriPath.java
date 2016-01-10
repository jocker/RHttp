package com.rhttp.lib.uri;

import com.rhttp.lib.Utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UriPath extends UriSegmentCollection{

    public static final String SEGMENT_DELIMITER = "/";

    private List<UriSegment> mSegments;

    private static class Segment extends UriSegment{
        public Segment(String rawStr) {
            super(rawStr);
        }

        @Override
        public void append(StringBuilder out, Map<String, ?> params) throws UnsupportedEncodingException {
            if(isCompilable()){
                out.append( Utils.urlEncode(String.valueOf(params.get(getTokenName()))) );
            }else{
                out.append(mRaw);
            }
        }
    }


    public UriPath() {
        super(SEGMENT_DELIMITER);
    }

    @Override
    protected void appendSegment(String rawSegment) {
        Segment segment = new Segment(rawSegment);
        if(mSegments == null){
            mSegments = new ArrayList<>();
        }
        mSegments.add(segment);
    }

    @Override
    protected Iterator<UriSegment> getSegments() {
        if(mSegments == null){
            return Collections.emptyIterator();
        }
        return mSegments.iterator();
    }

    @Override
    public UriPath cloneSelf() {
        UriPath instance = new UriPath();
        if(mSegments != null){
            instance.mSegments = new ArrayList<>();
            instance.mSegments.addAll(mSegments);
        }
        return instance;
    }
}
