package com.attlib.loadingdialog;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.attlib.loading.LoadingDialog;

public class MainActivity extends AppCompatActivity {
    private LoadingDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_show_loading).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncTask<Void, Void, Void>(){

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
    }

    private void showLoading() {
        if (progressDialog == null) {
            progressDialog = LoadingDialog.newInstance()
                    .setMessage("Loading data. \nPlease wait for a few seconds or check your internet quality ...", "#327773")
                    .setLoadingColor("#327773");
        }

        if (progressDialog.isAdded()) {
            return;
        }
        progressDialog.show(this);
    }

    private void dismissLoading() {
        if (progressDialog != null) progressDialog.dismiss();
    }

}
