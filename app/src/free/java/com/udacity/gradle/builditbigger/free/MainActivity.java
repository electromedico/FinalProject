package com.udacity.gradle.builditbigger.free;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.jokeprovider.JokeProvider;
import com.example.jokeshower.JokeShowerActivity;
import com.udacity.gradle.builditbigger.R;
import com.udacity.gradle.builditbigger.idlingresource.SimpleIdlingResource;
import com.udacity.gradle.builditbigger.networkUtils.EndpointsAsyncTask;

import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {
    JokeProvider jokeProvider;
    @Nullable
    private SimpleIdlingResource mIdlingResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jokeProvider = new JokeProvider();

        getIdlingResource();

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

    public void tellJoke(View view) throws ExecutionException, InterruptedException {

        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask();
        endpointsAsyncTask.execute();
        endpointsAsyncTask.get();
        String joke = endpointsAsyncTask.get();

        Intent intent = new Intent(this,JokeShowerActivity.class);
        intent.putExtra(JokeShowerActivity.INTENT_JOKE,joke);
        startActivity(intent);
        //Toast.makeText(this, joke, Toast.LENGTH_SHORT).show();
    }

    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource(){

        if (mIdlingResource == null) {
            mIdlingResource = new SimpleIdlingResource();
        }
        return mIdlingResource;
    }

}
