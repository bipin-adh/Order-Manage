package com.example.bpn8adh.ordermanage.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.bpn8adh.ordermanage.R;

/**
 * Created by Bipin Adhikari on 08/03/2018.
 */
public class DialogUtils {
    private static final String TAG = DialogUtils.class.getSimpleName();
    private static final String MSG_DIALOG_TITLE = "No Internet Connection Found";
    private static final String MSG_DIALOG = "Please check your Internet Connection.";
    private static final String MSG_EXIT = "Are you sure you want to exit?";
    private static final String TXT_POSITIVE = "Retry";
    private static final String OPTION_YES = "Yes";
    private static final String OPTION_NO = "No";
    private static final int REQUEST_CHECK_SETTINGS = 999;
    private static final String MSG_NOTIFICATION_RECEIVED = "Notification Received !!!";

    private static boolean isInternet = false;
    private static boolean isConnectionDialog = false;
    private static ProgressDialog progressDialog;
    private static Context context;

    public static void exitDialog(final Activity activity) {
        AlertDialog.Builder alertDlg = new AlertDialog.Builder(activity);
        alertDlg.setMessage(MSG_EXIT);
        alertDlg.setCancelable(false); // We avoid that the dialog can be cancelled, forcing the user to choose one of the options
        alertDlg.setPositiveButton(OPTION_YES, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.finishAffinity();
                System.exit(0);
            }
        });

        alertDlg.setNegativeButton(OPTION_NO, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertDlg.create().show();
    }

    /**
     * Returns {@link AlertDialog}
     *
     * @param context               {@link Context} of initiating activity
     * @param icon                  Icon to be shown in alertList
     * @param title                 {@link String} title of the dialog
     * @param msg                   {@link String} message to show on the dialog
     * @param view                  {@link View} Custom layout
     * @param positiveBtnText       {@link String} positive button text
     * @param positiveClickListener {@link DialogInterface.OnClickListener} positive button action
     * @param negativeBtnText       {@link String} positive button text
     * @param negativeClickListener {@link DialogInterface.OnClickListener} negative button action
     * @return
     */
    public static AlertDialog.Builder alertDialog(Context context, int icon, String title, String msg, View view, String positiveBtnText,
                                                  DialogInterface.OnClickListener positiveClickListener, String negativeBtnText,
                                                  DialogInterface.OnClickListener negativeClickListener) {

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);

//        return new AlertDialog.Builder(context)
        alertBuilder.setIcon(icon)
                .setTitle(title)
                .setMessage(msg)
                .setView(view)
                .setPositiveButton(positiveBtnText, positiveClickListener)
                .setNegativeButton(negativeBtnText, negativeClickListener)
                .setCancelable(false);

        AlertDialog dialog = alertBuilder.create();
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        alertBuilder.show();
        return alertBuilder;
    }

    /**
     * Returns simple progress dialog with message.
     *
     * @param context {@link Context} of the initiating activity
     * @param title   {@link String} title of the progress dialog
     * @param msg     {@link String} message to show in progress
     * @return {@link ProgressDialog}
     */
    public static ProgressDialog showProgressDialog(final Context context, String title, String msg, boolean indeterminate, boolean cancelable) {
        progressDialog = ProgressDialog.show(context,
                title,
                msg, indeterminate, cancelable,
                new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        Activity activity = (Activity) context;
                        activity.finish();
                    }
                }
        );

        return progressDialog;
    }

    /**
     * @param context     {@link Context} of the initiating activity
     * @param progressBar {@link ProgressBar}
     * @param uri         {@link String} image url in string
     * @param imageView   {@link ImageView}
     * @param priority
     */
    public static void showOfferGlideProgressDialog(final Context context, ProgressBar progressBar, String uri, ImageView imageView,
                                                    int overrideWidth, int overrideHeight, Priority priority) {

        progressBar.setVisibility(View.VISIBLE);
        final ProgressBar progressView = progressBar;
        Glide.with(context)
                .load("" + uri)
                .error(R.drawable.error_no_preview)
                .override(overrideWidth, overrideHeight)
                .diskCacheStrategy(DiskCacheStrategy.RESULT) // cache only the final image
                .priority(priority)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        progressView.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target,
                                                   boolean isFromMemoryCache, boolean isFirstResource) {
                        progressView.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(imageView);
    }


    /**
     * @param context     {@link Context} of the initiating activity
     * @param progressBar {@link ProgressBar}
     * @param uri         {@link String} image url in string
     * @param imageView   {@link ImageView}
     * @param priority
     */

    public static void showStoreGlideProgressDialog(final Context context, ProgressBar progressBar, String uri, ImageView imageView,
                                                    int overrideWidth, int overrideHeight, Priority priority) {

        progressBar.setVisibility(View.VISIBLE);
        final ProgressBar progressView = progressBar;
        Glide.with(context)
                .load("" + uri)
                .override(overrideWidth, overrideHeight)
                .diskCacheStrategy(DiskCacheStrategy.RESULT) // cache only the final image
                .priority(priority)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        progressView.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target,
                                                   boolean isFromMemoryCache, boolean isFirstResource) {
                        progressView.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(imageView);
    }
}
