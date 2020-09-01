package com.selflearning.chemistree;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.selflearning.chemistree.games.OnDialogFragmentShow;
import com.selflearning.chemistree.games.PreGameActivity;
import com.selflearning.chemistree.utilities.ActivityUtilities;

import java.util.Objects;

public class MenuDialogFragment extends DialogFragment
        implements View.OnClickListener {

    public static final String TAG = "MenuDialog";

    private Activity activity;
    private OnDialogFragmentShow dialogFragmentShow;

    private int type;

    public interface MyDialogListener{
        void isDialogShown(boolean isShow);
    }

//    private MyDialogListener dialogListener;


    public void setDialogFragmentShow(OnDialogFragmentShow dialogFragmentShow) {
        this.dialogFragmentShow = dialogFragmentShow;
    }

    public MenuDialogFragment(Activity activity, int type) {
        this.activity = activity;
        this.type = type;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_pause_game, container, false);

        Objects.requireNonNull(Objects.requireNonNull(getDialog()).getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Log.i("GameFr", "created");

//        dialogListener.isDialogShown(true);
        dialogFragmentShow.isDialogFragmentShown(true);

        v.findViewById(R.id.buttonMenu).setOnClickListener(this);
        v.findViewById(R.id.buttonResume).setOnClickListener(this);
        v.findViewById(R.id.buttonRestart).setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonMenu:
                onMenuClicked(v);
                break;
            case R.id.buttonResume:
                onResumeClicked(v);
                break;
            case R.id.buttonRestart:
                onRestartClicked(v);
                break;
        }
    }

    private void onMenuClicked(View v){
        dismiss();
        activity.finish();
    }

    private void onResumeClicked(View v){
        dismiss();
    }

    private void onRestartClicked(View v){
        dismiss();
        ActivityUtilities.getInstance().invokeNewActivity(activity, PreGameActivity.class,
                type, true);
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
//        dialogListener.isDialogShown(false);
        dialogFragmentShow.isDialogFragmentShown(false);
        Log.i("GameFr", "dismissed");
    }


}
