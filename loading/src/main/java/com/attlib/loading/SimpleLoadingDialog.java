package com.attlib.loading;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

public class SimpleLoadingDialog extends DialogFragment {
    private View mRootView;
    private ProgressBar mPbLoading;
    private TextView mTvMessage;

    private String mMessage;
    private String mMessageColor;

    private String mLoadingColor;

    private boolean mCanceledByBackButton = false;
    private boolean mCanceledByTouchOutSite = false;

    public SimpleLoadingDialog setMessage(String message, String colorString) {
        mMessage = message;
        mMessageColor = colorString;
        return this;
    }

    public SimpleLoadingDialog setLoadingColor(String loadingColor) {
        mLoadingColor = loadingColor;
        return this;
    }

    public SimpleLoadingDialog setCanceled(boolean canceledByBackButton, boolean canceledByTouchOutSite) {
        mCanceledByBackButton = canceledByBackButton;
        mCanceledByTouchOutSite = canceledByTouchOutSite;
        return this;
    }

    public static SimpleLoadingDialog newInstance() {
        return new SimpleLoadingDialog();
    }

    public void show(FragmentActivity activity) {
        if (activity != null && activity.getSupportFragmentManager() != null) {
            this.show(activity.getSupportFragmentManager(), "SimpleLoadingDialog");
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
        dialog.setCancelable(mCanceledByBackButton);
        dialog.setCanceledOnTouchOutside(mCanceledByTouchOutSite);
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

    @Override
    public void onResume() {
        super.onResume();

        getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(android.content.DialogInterface dialog,
                                 int keyCode, android.view.KeyEvent event) {
                if ((keyCode == android.view.KeyEvent.KEYCODE_BACK)) {
                    return !mCanceledByBackButton;
                }
                // Otherwise, do nothing else
                else return false;
            }
        });
    }
}
