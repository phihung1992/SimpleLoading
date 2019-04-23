package com.attlib.loading;

import android.app.Dialog;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LoadingDialog extends DialogFragment {
    private View mRootView;
    private ProgressBar mPbLoading;
    private TextView mTvMessage;

    private String mMessage;
    private String mMessageColor;

    private String mLoadingColor;

    public LoadingDialog setMessage(String message, String colorString) {
        mMessage = message;
        mMessageColor = colorString;
        return this;
    }

    public LoadingDialog setLoadingColor(String loadingColor) {
        mLoadingColor = loadingColor;

        return this;
    }

    public static LoadingDialog newInstance() {
        return new LoadingDialog();
    }

    public void show(FragmentActivity activity) {
        if (activity != null && activity.getSupportFragmentManager() != null) {
            this.show(activity.getSupportFragmentManager(), "LoadingDialog");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.dialog_loading, container, false);
        initViews();
        return mRootView;
    }

    private void initViews() {
        mPbLoading = mRootView.findViewById(R.id.pb_loading);
        mTvMessage = mRootView.findViewById(R.id.tv_loading_content);

        if (mLoadingColor != null && !mLoadingColor.trim().isEmpty() && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mPbLoading.setIndeterminateTintList(ColorStateList.valueOf(Color.parseColor(mLoadingColor)));
        }

        if (mMessage != null && !mMessage.trim().isEmpty()) {
            mTvMessage.setVisibility(View.VISIBLE);
            mTvMessage.setText(mMessage);
            mTvMessage.setTextColor(Color.parseColor(mMessageColor));
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog().getWindow() != null) {
            getDialog().getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

}
