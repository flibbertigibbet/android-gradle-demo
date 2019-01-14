package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.banderkat.jokeactivitylibrary.ShowJokeActivity;
import com.banderkat.jokes.Jokes;

import static com.banderkat.jokeactivitylibrary.ShowJokeActivity.JOKE_BUNDLE_ID;


public class MainActivity extends AppCompatActivity {

    private Jokes jokes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jokes = new Jokes();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        Intent intent = new Intent(this, ShowJokeActivity.class);
        intent.putExtra(JOKE_BUNDLE_ID, jokes.getJoke());
        startActivity(intent);
    }


}
