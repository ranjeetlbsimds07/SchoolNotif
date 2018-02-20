package board.notif.school.com.schoolnotifboard.helper;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.Html;
import android.text.TextUtils;
import android.util.Base64;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import org.apache.http.NameValuePair;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import board.notif.school.com.schoolnotifboard.R;


/**
 * Created by Guest User on 1/30/2018.
 */

public class DataStatic {
    /**
     * email validation
     * @param email
     * @return
     */
    public static boolean emailValidator(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }


    /**
     * for check internet connection..
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
    /**
     * Show an alert wherever you like, you can pass any field null except
     * activity.
     *
     * @param activity
     * @param title
     * @param message
     * @param buttonText
     * @param isAlertCancelable
     * @param finishActivityOnClickingOK
     */
    public static void showAlert(final Activity activity, String title,
                                 String message, String buttonText, boolean isAlertCancelable,
                                 final boolean finishActivityOnClickingOK) {

        int paddingLeft = (int) activity.getResources().getDimension(
                R.dimen.d11);
        int paddingTop = (int) activity.getResources().getDimension(R.dimen.d7);

        TextView head = new TextView(activity);
        head.setText(title);
        head.setPadding(paddingLeft, paddingTop, paddingLeft, paddingTop);
        head.setGravity(Gravity.CENTER);
        head.setTextSize((int) (activity.getResources().getDimension(
                R.dimen.font_ll) / activity.getResources().getDisplayMetrics().density));

        ScrollView scroller = new ScrollView(activity);
        TextView msg = new TextView(activity);
        msg.setText(Html.fromHtml(message));
        msg.setPadding(paddingLeft, paddingTop, paddingLeft, paddingTop);
        msg.setGravity(Gravity.CENTER);
        msg.setTextSize((int) (activity.getResources().getDimension(
                R.dimen.font_l) / activity.getResources().getDisplayMetrics().density));
        scroller.addView(msg);

        if (TextUtils.isEmpty(buttonText))
            buttonText = "OK";

        AlertDialog.Builder dialog = new AlertDialog.Builder(activity);

        if (isAlertCancelable)
            dialog.setCancelable(isAlertCancelable);
        if (!TextUtils.isEmpty(title)) {
            dialog.setCustomTitle(head);
        }
        dialog.setView(scroller);
        dialog.setPositiveButton(buttonText,
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        if (finishActivityOnClickingOK) {
                            activity.finish();
                        }
                        dialog.dismiss();

                    }
                });
        //TODO need to check why it is here
        final AlertDialog alertDialog = dialog.show();
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Button btnPositive = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
                btnPositive.setTextSize(activity.getResources().getDimension(R.dimen.d5));
                Button btnNegative = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
                btnNegative.setTextSize(activity.getResources().getDimension(R.dimen.d5));
            }
        });
    }
    /**
     * Show an alert wherever you like, you can pass any field null except
     * activity.
     *
     * @param activity
     * @param title
     * @param message
     * @param buttonText
     * @param isAlertCancelable
     * @param finishActivityOnClickingOK
     */
    public static void showCustomAlert(final Activity activity, String title,
                                       String message, String buttonText, boolean isAlertCancelable,
                                       final boolean finishActivityOnClickingOK){
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.setTitle(title);

        // set the custom dialog components - text, image and button
        TextView text = (TextView) dialog.findViewById(R.id.text);
        text.setText(message);
        Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    /**
     * Start Progress Bar
     * @param progressDialog
     * @param activity
     */
    public static void progressDialogStart(ProgressDialog progressDialog, final Activity activity){

        progressDialog.setMessage(activity.getResources().getString(R.string.Please_wait)); // Setting Message
        //progressDialog.setTitle(activity.getResources().getString(R.string.Please_wait)); // Setting Title
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
        progressDialog.show(); // Display Progress Dialog
        progressDialog.setCancelable(false);
        progressDialog.onStart();

    }

    /**
     * Stop progress bar
     */
    public static void progressDialogStop(ProgressDialog progressDialog){
        progressDialog.dismiss();
    }

    /**
     * convert Namevalue Pair value in Strings
     * @param params
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getQuery(ArrayList<NameValuePair> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (NameValuePair pair : params)
        {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
        }

        return result.toString();
    }

    /**
     * set Api key and Api secert key
     * @return
     */
    public static String authString(){
        String baseAuthStr = AppConstants.APIKEY + ":" + AppConstants.APISECRET;
        final String basicAuth = "Basic " + Base64.encodeToString(baseAuthStr.getBytes(), Base64.DEFAULT);
        return basicAuth;
    }


}
