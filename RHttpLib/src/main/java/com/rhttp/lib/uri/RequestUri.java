package com.rhttp.lib.uri;

import com.rhttp.lib.Utils;
import com.squareup.okhttp.RequestBody;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RequestUri {

    public static class Builder{
        private String mScheme, mAuthority;
        private Integer mPort;
        private UriPath mPath;
        private UriQuery mQuery;

        private Builder(String rawUri){
            this(URI.create(rawUri));
        }

        private Builder(URI uri){
            if(uri != null){
                mAuthority = uri.getRawAuthority();
                mScheme = uri.getScheme();
                mPort = uri.getPort();
                if(mPort < 0){
                    mPort = null;
                }
                String path = uri.getRawPath();
                if(!Utils.isNullOrEmpty(path)){
                    path = Utils.stripTrailing(path, UriPath.SEGMENT_DELIMITER);
                }
                if(!Utils.isNullOrEmpty(path)){
                    mPath = new UriPath();
                    mPath.append(path);
                }

                String query = uri.getRawQuery();
                if(!Utils.isNullOrEmpty(query)){
                    query = Utils.stripTrailing(query, UriQuery.SEGMENT_DELIMITER);
                }
                if(!Utils.isNullOrEmpty(query)){
                    mQuery = new UriQuery();
                    mQuery.append(query);
                }
            }
        }

        private Builder(Builder other){
            Builder instance = new Builder();
            if(other != null){
                if(!Utils.isNullOrEmpty(other.mAuthority)){
                    instance.mAuthority = other.mAuthority;
                }
                if(!Utils.isNullOrEmpty(other.mScheme)){
                    instance.mScheme = other.mScheme;
                }
                if(other.mPort != null){
                    instance.mPort = other.mPort;
                }
                if(other.mPath != null){
                    instance.mPath = other.mPath.cloneSelf();
                }
                if(other.mQuery != null){
                    instance.mQuery = other.mQuery.cloneSelf();
                }
            }

        }

        private Builder(){

        }

        public Builder setScheme(String scheme){
            mScheme = scheme;
            return this;
        }

        public Builder setAuthority(String authority){
            mAuthority = authority;
            return this;
        }

        public Builder setPort(Integer port){
            mPort = port;
            return this;
        }

        public Builder appendPath(Object... segments){
            for(Object path: segments){
                getUriPath().append(String.valueOf(path));
            }
            return this;
        }

        public Builder appendQuery(Object... segments){
            for(Object query: segments){
                getUriQuery().append(String.valueOf(query));
            }
            return this;
        }

        public Builder appendQuery(String key, String value){
            appendQuery(new UriQuery.Segment(key, value));
            return this;
        }

        public Builder appendQuery(Map<String, String> queryMap){
            for(Map.Entry<String, String> entry: queryMap.entrySet()){
                appendQuery(entry.getKey(), entry.getValue());
            }
            return this;
        }

        private Builder appendQuery(UriQuery.Segment segment){
            getUriQuery().appendSegment(segment);
            return this;
        }


        private UriPath getUriPath(){
            if(mPath == null){
                mPath = new UriPath();
            }
            return mPath;
        }

        private UriQuery getUriQuery(){
            if(mQuery == null){
                mQuery = new UriQuery();
            }
            return mQuery;
        }

        protected Set<String> getRequiredQueryParams(){
            return extractRequiredParams(mQuery);
        }

        protected Set<String> getRequiredPathParams(){
            return extractRequiredParams(mPath);
        }

        private Set<String> extractRequiredParams(UriSegmentCollection collection){
            if(collection == null || collection.isEmpty()){
                return Collections.emptySet();
            }
            Iterator<UriSegment> it = collection.getSegments();
            Set<String> dependencies = null;
            while(it.hasNext()){
                UriSegment segment  =it.next();
                if(segment.isCompilable()){
                    if(dependencies == null){
                        dependencies = new HashSet<>();
                    }
                    dependencies.add(segment.getTokenName());
                }
            }
            return dependencies;
        }
    }
}
