package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.banderkat.jokeactivitylibrary.ShowJokeActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;
import java.lang.ref.WeakReference;

import static com.banderkat.jokeactivitylibrary.ShowJokeActivity.JOKE_BUNDLE_ID;

// Based on:
// https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/77e9910911d5412e5efede5fa681ec105a0f02ad/HelloEndpoints#2-connecting-your-android-app-to-the-backend
public class QueryJokeAsyncTask extends AsyncTask<Context, Void, String> {

    private static final String LOG_LABEL = "QueryAsyncTask";
    private static MyApi myApiService = null;
    private WeakReference<Context> contextRef;

    @Override
    protected String doInBackground(Context... params) {

        contextRef = new WeakReference<>(params[0]);

        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)

                    .setRootUrl("https://builditbigger-228916.appspot.com/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            myApiService = builder.build();
        }

        try {
            return myApiService.sayJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String joke) {
        Log.d(LOG_LABEL, "Got joke " + joke);

        Context context = contextRef.get();
        if (context == null) {
            Log.w(LOG_LABEL, "Context reference is gone; not showing joke");
            return;
        }

        Log.d(LOG_LABEL, "Go show joke");
        Intent intent = new Intent(context, ShowJokeActivity.class);
        intent.putExtra(JOKE_BUNDLE_ID, joke);
        context.startActivity(intent);
    }
}
