package edu.ucsd.troll.app;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;


public class MainActivity extends Activity {

    //Api varaibles
    private static String url = "http://troll.everythingcoed.com/get/fact";
    private static final String TAG_ID = "id";
    private static final String TAG_FACT = "fact";
    public String factString = null;

    JSONArray fact = null;

    //store the get values
    List<NameValuePair> params = new ArrayList<NameValuePair>();

    // Alert Dialog Manager
    AlertDialogManager alert = new AlertDialogManager();

    // Session Manager Class
    SessionManager session;

    APILoginHandler loginManager;

    TextView factTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        factTextView = (TextView) findViewById(R.id.factText);

        params.add(new BasicNameValuePair("api_key", "OlDwjUX0fQSm0vAy2D3fy4uCZ108bx5N"));

        //get random fact
        new GetRandomFact().execute();


        //factTextView.setText(factString);

       // factTextView.setText(factString);

        final Button switchToMaps = (Button) findViewById(R.id.mapsBtn);
        final Button switchToMenu = (Button) findViewById(R.id.menuBtn);
        final Button switchToProfile = (Button) findViewById(R.id.profileBtn);

        switchToMaps.setOnClickListener(new View.OnClickListener() {
            @Override

        public void onClick(View view) {
                Intent act2 = new Intent(view.getContext(), MapsActivity.class);
                startActivity(act2);
            }
        });


        switchToMenu.setOnClickListener(new View.OnClickListener() {
           @Override

           public void onClick(View view) {
                Intent act3 = new Intent(view.getContext(), LocationMenuActivity.class);
                startActivity(act3);
           }
        });


        switchToProfile.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                Intent act4 = new Intent(view.getContext(), ProfileActivity.class);
                startActivity(act4);
            }
        });
    }



    private class GetRandomFact extends AsyncTask<Void, Void, String> {


        @Override
        protected String doInBackground(Void... arg0) {
            // Creating service handler class instance
            APIServiceHandler sh = new APIServiceHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url, APIServiceHandler.GET, params);

            Log.d("Response: ", "=> " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    //Log.d("Response: ", "=> " + jsonStr);

                    String id = jsonObj.getString(TAG_ID);

                    //Log.d("ID: ", "=> " + id);

                    String theFact = jsonObj.getString(TAG_FACT);

                    //Log.d("Fact: ", "=> " + theFact);

                    return theFact;

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }

            return null;
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            factTextView.setText(result);

        }

    }
}