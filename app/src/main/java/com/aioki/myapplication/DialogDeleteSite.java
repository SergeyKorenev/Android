package com.aioki.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.aioki.myapplication.Activity.SiteListActivity;
import com.aioki.myapplication.DB.DBHandler;


public class DialogDeleteSite extends DialogFragment {

    SiteListActivity activity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (SiteListActivity) context;
    }

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Integer siteID = getArguments().getInt("SiteID");

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        return builder.setTitle("Confirm your action")
                .setMessage("Do you really want to delete this source?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBHandler db = new DBHandler(activity);
                        db.deleteSite(siteID);
                        activity.updateList();
                    }
                })
                .setNegativeButton("Cancel",null)
                .create();
    }
}

