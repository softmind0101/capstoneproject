package com.matanmi.project.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.widget.ProgressBar;

import com.matanmi.project.database.DatabaseHelper;

/*
 * Util        : LoadingTask.java
 * Date        : 2016
 * Version     : 1.00
 * Author      : Matanmi Falana
 * Copyright (c) 2016
 */

public class LoadingTask extends AsyncTask<String, Integer, Integer> {

    public interface LoadingTaskFinishedListener {
        void onTaskFinished(); // If you want to pass something back to the listener add a param to this method
    }
    private static final String DB_NAME = "Capstone";
    private static final int DB_VERSION = 2;
    private DatabaseHelper dbHelper = null;
    private SQLiteDatabase db = null;
    // This is the progress bar you want to update while the task is in progress
    private final ProgressBar progressBar;
    // This is the listener that will be told when this task is finished
    private final LoadingTaskFinishedListener finishedListener;

    /**
     * A Loading task that will load some resources that are necessary for the app to start
     * @param progressBar - the progress bar you want to update while the task is in progress
     * @param finishedListener - the listener that will be told when this task is finished
     */
    public LoadingTask(ProgressBar progressBar, LoadingTaskFinishedListener finishedListener, Context context) {
        this.progressBar = progressBar;
        this.finishedListener = finishedListener;
        this.dbHelper = new DatabaseHelper(context);
    }

    @Override
    protected Integer doInBackground(String... params) {
        db = dbHelper.getWritableDatabase();
        if(resourcesDontAlreadyExist()){
            downloadResources();
        }
        // Perhaps you want to return something to your post execute
        return 1234;
    }

    private boolean resourcesDontAlreadyExist() {
        // Here you would query your app's internal state to see if this download had been performed before
        // Perhaps once checked save this in a shared preference for speed of access next time
        return true; // returning true so we show the splash every time
    }


    private void downloadResources() {
        // We are just imitating some process that takes a bit of time (loading of resources/downloading)
        int count = 30;
        for (int i = 0; i < count; i++) {
            // Update the progress bar after every step
            int progress = (int) ((i / (float) count) * 100);
            publishProgress(progress);
            // Do some long loading things
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignore) {

            }
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressBar.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
        progressBar.setProgress(values[0]); // This is ran on the UI thread so it is ok to update our progress bar ( a UI view ) here
    }

    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);
        finishedListener.onTaskFinished(); // Tell whoever was listening we have finished
    }
}