package com.rxnet.rhttp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

import rx.Observable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final OkHttpClient client = new OkHttpClient();

        RequestBody.create()
        Request req = new Request.Builder()
                .url("http://api.reverbnation.com/artist/149/songs")
                .build();

        Response x;



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    static class HttpCall extends Call{

        public static HttpCall from(OkHttpClient client, Request originalRequest){
            return new HttpCall(client, originalRequest);
        }

        protected HttpCall(OkHttpClient client, Request originalRequest) {
            super(client, originalRequest);

        }

        @Override
        public Response execute() throws IOException {
            return super.execute();
        }
    }

}


