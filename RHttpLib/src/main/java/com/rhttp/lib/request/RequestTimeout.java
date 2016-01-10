package com.rhttp.lib.request;

import java.util.concurrent.TimeUnit;

public class RequestTimeout {
    public static final int CONNECT = 0, READ = 1, WRITE = 2;
    private long[] mSpec;

    public void set(int type, TimeUnit unit, long amount){
        if(type >=0 && type < 3){
            if(mSpec == null){
                mSpec = new long[3];
            }
            mSpec[type] = TimeUnit.MILLISECONDS.convert(amount, unit);
        }
    }


    public long get(int type){
        return type >=0 && type<3 && mSpec != null ? mSpec[type] : 0;
    }

    public boolean isEmpty(){
        return mSpec == null;
    }
}
