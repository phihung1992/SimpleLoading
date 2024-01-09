package com.attlib.loading;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AnimLoadingDialog extends DialogFragment {
    private View mRootView;
    private ImageView mIvAnimationImage;
    private TextView mTvMessage;

    private AnimationDrawable mImageDrawable;
    private int mBackgroundColor = 0;

    private String mMessage;
    private String mMessageColor;

    private boolean mCanceledByBackButton = false;
    private boolean mCanceledByTouchOutSite = false;

    private boolean mIsFullScreen = true;

    public AnimLoadingDialog setMessage(String message, String colorString) {
        mMessage = message;
        mMessageColor = colorString;
        return this;
    }

    public AnimLoadingDialog setCanceled(boolean canceledByBackButton, boolean canceledByTouchOutSite) {
        mCanceledByBackButton = canceledByBackButton;
        mCanceledByTouchOutSite = canceledByTouchOutSite;
        return this;
    }

    public AnimLoadingDialog setAnimationDrawable(AnimationDrawable imageDrawable) {
        mImageDrawable = imageDrawable;
        return this;
    }

    public AnimLoadingDialog setBackgroundColor(int color) {
        mBackgroundColor = color;
        return this;
    }

    public AnimLoadingDialog setFullScreen(boolean value) {
        mIsFullScreen = value;
        return this;
    }

    public static AnimLoadingDialog newInstance() {
        return new AnimLoadingDialog();
    }

    public void show(FragmentActivity activity) {
        if (activity != null && activity.getSupportFragmentManager() != null) {
            this.show(activity.getSupportFragmentManager(), "AnimLoadingDialog");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mIsFullScreen) {
            mRootView = inflater.inflate(R.layout.dialog_animation_loading, container, false);
        } else {
            mRootView = inflater.inflate(R.layout.dialog_animation_loading_wrap_content, container, false);
        }

        initViews();
        return mRootView;
    }

    private void initViews() {
        LinearLayout lnMain = mRootView.findViewById(R.id.ln_main);
        if (mIsFullScreen) {
            if (mBackgroundColor == 0) {
                lnMain.setBackgroundColor(Color.WHITE);
            } else {
                lnMain.setBackgroundColor(mBackgroundColor);
            }
        }

        mIvAnimationImage = mRootView.findViewById(R.id.iv_animation_image);
        mTvMessage = mRootView.findViewById(R.id.tv_loading_content);

        if (mImageDrawable != null) {
            mIvAnimationImage.setImageDrawable(mImageDrawable);
            mImageDrawable.start();
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
            if (mIsFullScreen) {
                getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            } else {
                getDialog().getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            }

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
