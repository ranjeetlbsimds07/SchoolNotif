package board.notif.school.com.schoolnotifboard.asyntask;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import board.notif.school.com.schoolnotifboard.helper.DataStatic;


/**
 * Created by Guest User on 1/30/2018.
 */

public class GetHitAsynTask extends AsyncTask<String, String, UIMessage> {
    private ResponseListener callback;
    private String url;
    private String cookieString = "";
    String serviceName = "";


    public GetHitAsynTask(String url, ResponseListener callback, String serviceName) {
        this.callback = callback;
        this.url = url;
        this.serviceName = serviceName;

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
            conn.setRequestMethod("GET");
            conn.setDoOutput(false);
            //conn.setRequestProperty(AppConstants.ACCEPT_LANGUAGE, "En");
            conn.setRequestProperty("Authorization", DataStatic.authString());
            responseCode = conn.getResponseCode();
            if (conn.getInputStream() != null) {
                String line;
                BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line=br.readLine()) != null) {
                    response+=line;
                }
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

