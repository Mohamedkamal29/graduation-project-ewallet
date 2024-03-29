package com.graduation.ewallet.Utiles;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.widget.ProgressBar;

import com.graduation.ewallet.R;


/**
 * Created by Mahmoud on 10/16/17.
 */

public final class DialogUtil {

    private DialogUtil() {
    }

    public static ProgressDialog showProgressDialog(Context context, String message, boolean cancelable) {
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setMessage(message);
        dialog.setCancelable(cancelable);
        dialog.show();
        ProgressBar progressbar=(ProgressBar)dialog.findViewById(android.R.id.progress);
        progressbar.getIndeterminateDrawable().setColorFilter(context.getColor(R.color.colorAccent), android.graphics.PorterDuff.Mode.SRC_IN);
        return dialog;
    }


    public static AlertDialog showAlertDialog(Context context, String message,
                                              DialogInterface.OnClickListener negativeClickListener) {
        // create the dialog builder & set message
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setTitle(message);
        // check negative click listener
        if (negativeClickListener != null) {
            // not null
            // add negative click listener
            dialogBuilder.setNegativeButton(context.getString(R.string.yes), negativeClickListener);
        } else {
            // null
            // add new click listener to dismiss the dialog
            dialogBuilder.setNegativeButton(context.getString(R.string.no), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
        }
        // create and show the dialog

        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
        return dialog;
    }

    public static AlertDialog showconfirm(Context context, int tittle, String message, boolean cancble,
                                          DialogInterface.OnClickListener negativeClickListener, DialogInterface.OnClickListener positiveClickListener) {
        // create the dialog builder & set message
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setTitle(tittle);

        dialogBuilder.setMessage(message);

        // check negative click listener
        if (negativeClickListener != null) {
            // not null
            // add negative click listener
            dialogBuilder.setNegativeButton(context.getString(R.string.cancel), negativeClickListener);
        }
        if (positiveClickListener != null) {
            // not null
            // add negative click listener
            dialogBuilder.setPositiveButton(context.getString(R.string.dialog_continue), positiveClickListener);
        }
        // create and show the dialog
        dialogBuilder.setCancelable(cancble);
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();

        return dialog;
    }
}
