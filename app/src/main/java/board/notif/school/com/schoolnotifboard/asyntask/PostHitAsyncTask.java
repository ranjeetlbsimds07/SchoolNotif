package board.notif.school.com.schoolnotifboard.asyntask;

import android.os.AsyncTask;

import org.apache.http.NameValuePair;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import board.notif.school.com.schoolnotifboard.helper.DataStatic;


/**
 * Created by Guest User on 1/30/2018.
 */

public class PostHitAsyncTask extends AsyncTask<String, String, UIMessage> {
    private String url;
    private String serviceName;
    private ArrayList<NameValuePair> nameValuePairs;
    private String TAG = PostHitAsyncTask.class.getSimpleName();
    private ResponseListener callback;

    public PostHitAsyncTask(String url, ArrayList<NameValuePair> nameValuePairs,String serviceName, ResponseListener callback) {
        this.url = url;
        this.nameValuePairs = nameValuePairs;
        this.serviceName = serviceName;
        this.callback = callback;
    }


    @Override
    protected UIMessage doInBackground(String... params) {

        URL url;
        String response = "";
        int responseCode = -1;
        try {
            url = new URL(this.url);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(45000);
            conn.setConnectTimeout(45000);
            conn.setRequestMethod("POST");
            //conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            //conn.setRequestProperty(AppConstants.ACCEPT_LANGUAGE, "En");
            //String baseAuthStr = AppConstants.APIKEY + ":" + AppConstants.APISECRET;
            //final String basicAuth = "Basic " + Base64.encodeToString(baseAuthStr.getBytes(), Base64.DEFAULT);
            //conn.setRequestProperty("Authorization",DataStatic.authString());

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(DataStatic.getQuery(nameValuePairs));

            writer.flush();
            writer.close();
            os.close();
            conn.setInstanceFollowRedirects(true);
            responseCode = conn.getResponseCode();

            if (conn.getInputStream() != null) {
                String line;
                BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line=br.readLine()) != null) {
                    response+=line;
                }
            } else if(conn.getErrorStream() != null) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                while ((line = br.readLine()) != null) {
                    response += line;
                }
                // response would be like: {"code":401,"reason":"Unauthorized","message":"Authentication Failed"}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new UIMessage().setResponseCode(responseCode).setScreenData(response);
    }


    @Override
    protected void onPostExecute(UIMessage result) {
        callback.responseOfServiceHit(result, serviceName);
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected void onProgressUpdate(String... text) {
    }
}

