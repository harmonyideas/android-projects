package com.harmonyideas.nettester2;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by HarmonyIdeas on 2/16/2017.
 */

public class SendPost extends AsyncTask<String, int[], String> {

    private String nwTest;
    private String responseText = "";
    private final String USER_AGENT = "Mozilla/5.0";
    private ProgressDialog dialog;
    public AsyncResponse delegate = null;
    public Context cxt;

    public interface AsyncResponse {
        void processFinish(String output);
    }

    public SendPost(AsyncResponse delegate, Context cxt, String nwTest) {
        this.delegate = delegate;
        this.cxt = cxt;
        dialog = new ProgressDialog(cxt);
        this.nwTest = nwTest;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog.setMessage("Working...");
        dialog.show();
    }

    @Override
    protected void onPostExecute(String responseText) {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
        //super.onPostExecute(responseText);
        delegate.processFinish(responseText);
    }

    @Override
    protected String doInBackground(String... params) {

        try {
            String url = "http://example.com" + nwTest;
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            //add request header
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            String urlParameters = "host=" + params[0];

            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + urlParameters);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            responseText = response.toString();
            responseText = responseText.replace("\\r", "");
            responseText = responseText.replace("\\t", "");

        } catch (final Exception e) {
            responseText = e.toString();
        }

        /*
        Anything done here is in a separate thread to the UI thread
        If you want to update the progress you can call
        This passes the String to the onPostExecute method.
        Most likely not the right way to do this but still learning.
        */

        return responseText;
    }

}



