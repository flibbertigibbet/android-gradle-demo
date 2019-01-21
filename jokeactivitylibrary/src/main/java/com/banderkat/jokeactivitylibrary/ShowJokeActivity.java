package com.banderkat.jokeactivitylibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ShowJokeActivity extends AppCompatActivity {

    public static final String JOKE_BUNDLE_ID = "joke";
    private static final String LOG_LABEL = "ShowJoke";

    private String joke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_joke);

        Bundle extras = getIntent().getExtras();

        if (extras != null && extras.containsKey(JOKE_BUNDLE_ID)) {
            joke = extras.getString(JOKE_BUNDLE_ID);
        } else {
            Log.w(LOG_LABEL, "No joke found in bundle!");
        }

        TextView textView = findViewById(R.id.show_joke_text);
        textView.setText(joke);
    }
}
