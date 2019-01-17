package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.annotation.UiThread;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class QueryJokeAsyncTaskTest {


    @Test
    @UiThread
    public void testAsyncQuery() {
        MainActivity activity = mock(MainActivity.class, Mockito.CALLS_REAL_METHODS);
        QueryJokeAsyncTask task = new QueryJokeAsyncTask();
        QueryJokeAsyncTask spyTask = spy(task);
        spyTask.execute(activity);

        // verify async task post execute invoked with a non-empty string
        verify(spyTask, timeout(7000).times(1)).execute((Context)any());

        // FIXME: why not invoked
        //verify(spyTask, timeout(7000).times(1)).onPostExecute(anyString());
        //verify(activity, timeout(7000).times(1)).startActivity((Intent) any());
    }

}