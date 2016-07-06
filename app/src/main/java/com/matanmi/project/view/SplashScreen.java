package com.matanmi.project.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.matanmi.project.R;
import com.matanmi.project.util.LoadingTask;
import com.matanmi.project.util.LoadingTask.LoadingTaskFinishedListener;

public class SplashScreen extends Activity implements LoadingTaskFinishedListener {

    private Intent intentInstance = null;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        progressBar = (ProgressBar) findViewById(R.id.progression_bar);
        progressBar.setProgress(0);
        progressBar.setMax(100);
        new LoadingTask(progressBar, this, SplashScreen.this).execute("");
    }

    // This is the callback for when your async task has finished
    @Override
    public void onTaskFinished() {
        completeSplash();
    }

    private void completeSplash(){
        startApp();
        finish(); // Don't forget to finish this Splash Activity so the user can't return to it!
    }

    private void startApp() {
        intentInstance = new Intent(SplashScreen.this, MainActivity.class);
        startActivity(intentInstance);
    }
}
