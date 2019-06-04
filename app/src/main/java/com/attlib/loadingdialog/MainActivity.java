package com.attlib.loadingdialog;

import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.attlib.loading.AnimLoadingDialog;
import com.attlib.loading.SimpleLoadingDialog;

public class MainActivity extends AppCompatActivity {
    private SimpleLoadingDialog progressDialog;
    private AnimLoadingDialog animLoadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_show_loading).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncTask<Void, Void, Void>() {

                    @Override
                    protected void onPreExecute() {
                        showLoading();
                    }

                    @Override
                    protected Void doInBackground(Void... voids) {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        dismissLoading();
                    }
                }.execute();
            }
        });

        findViewById(R.id.btn_show_anim_loading).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncTask<Void, Void, Void>() {

                    @Override
                    protected void onPreExecute() {
                        showAnimLoading();
                    }

                    @Override
                    protected Void doInBackground(Void... voids) {
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        dismissAnimLoading();
                    }
                }.execute();
            }
        });
    }

    private void showLoading() {
        if (progressDialog == null) {
            progressDialog = SimpleLoadingDialog.newInstance()
                    .setMessage("Loading data. \nPlease wait for a few seconds or check your internet quality ...", "#327773")
                    .setLoadingColor("#327773")
                    .setCanceled(true, true);
        }

        if (progressDialog.isAdded()) {
            return;
        }
        progressDialog.show(this);
    }

    private void dismissLoading() {
        if (progressDialog != null) progressDialog.dismiss();
    }

    private AnimationDrawable getLoadingAnimationDrawable() {
        AnimationDrawable animationDrawable = new AnimationDrawable();
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.loading_img_1), 70);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.loading_img_2), 70);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.loading_img_3), 70);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.loading_img_4), 70);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.loading_img_5), 70);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.loading_img_6), 70);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.loading_img_7), 70);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.loading_img_8), 70);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.loading_img_9), 70);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.loading_img_10), 70);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.loading_img_11), 70);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.loading_img_12), 70);
        animationDrawable.setOneShot(false);
        return animationDrawable;
    }

    private void showAnimLoading() {
        if (animLoadingDialog == null) {
            animLoadingDialog = AnimLoadingDialog.newInstance()
                    .setMessage("Loading data. \nPlease wait for a few seconds or check your internet quality ...", "#327773")
                    .setAnimationDrawable(getLoadingAnimationDrawable())
                    .setBackgroundColor(Color.parseColor("#AB3848"))
                    .setCanceled(true, true);
        }

        if (animLoadingDialog.isAdded()) {
            return;
        }
        animLoadingDialog.show(this);
    }

    private void dismissAnimLoading() {
        if (animLoadingDialog != null) animLoadingDialog.dismiss();
    }

}
