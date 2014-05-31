package edu.ucsd.troll.app;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.app.ActionBar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.ArrayList;

import org.apache.http.NameValuePair;

import java.util.HashMap;

import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import edu.ucsd.troll.app.R;

/**
 * Created by shalomabitan on 5/22/14.
 */
public class ProfileActivity extends Activity {

    //init the progress bar
    private ProgressDialog pDialog;

    // URL to get menu JSON
    private static String url = "http://troll.everythingcoed.com/user/login";

    // JSON Node names
    private static final String TAG_CONTACTS = "menu";
    private static final String TAG_ID = "id";
    private static final String TAG_TITLE = "title";
    private static final String TAG_DESCRIPTION = "description";
    private static final String TAG_CATEGORY = "category";
    private static final String TAG_RATING = "rating";
    private static final String TAG_SIZES = "sizes";
    private static final String TAG_SIZE = "size";
    private static final String TAG_PRICE = "price";
    //private static final String TAG_PHONE_OFFICE = "office";

    // menu JSONArray
    JSONArray menu = null;

    // Hashmap for ListView
    ArrayList<HashMap<String, String>> favoriteList;

    List<NameValuePair> params = new ArrayList<NameValuePair>();

    // Alert Dialog Manager
    AlertDialogManager alert = new AlertDialogManager();

    LoginManager login;
    //login button
    //Button logoutButton;

    //Edittext
    EditText usernameTextBox, passwordTextBox;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        getActionBar().setDisplayHomeAsUpEnabled(true);

        //login manager
        login = new LoginManager(getApplicationContext());
        
        if(!login.isLoggedIn()){
        	
            setContentView(R.layout.profile_logged_out);

            
            final Button switchToSignIn = (Button) findViewById(R.id.signInBtn);
            final Button switchToSignUp = (Button) findViewById(R.id.signUpBtn);

            switchToSignIn.setOnClickListener(new View.OnClickListener() {
                @Override

            public void onClick(View view) {
                	Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(i);
                    finish();
                }
            });
            
            switchToSignUp.setOnClickListener(new View.OnClickListener() {
                @Override

            public void onClick(View view) {
                	Intent i = new Intent(getApplicationContext(), SignUpActivity.class);
                    startActivity(i);
                    finish();
                }
            });

        }else{
            setContentView(R.layout.profile_layout);
            //this is just for testing and debugging
            //login.logoutUser();
         
            
            final Button logoutButton = (Button) findViewById(R.id.logoutBtn);
            final Button favorites = (Button) findViewById(R.id.favBtn);

            logoutButton.setOnClickListener(new View.OnClickListener() {
                @Override

            public void onClick(View view) {
                	login.logoutUser();
                    Intent act2 = new Intent(view.getContext(), MainActivity.class);
                    Toast.makeText(ProfileActivity.this, "You Have Been Logged out", Toast.LENGTH_LONG).show();
                    startActivity(act2);
                }
            });
            
                        
            favorites.setOnClickListener(new View.OnClickListener() {
                @Override
                
            public void onClick(View view) {
                    Intent i = new Intent(getApplicationContext(), FavoritesActivity.class);
                    startActivity(i);
                    finish();
                }
            });

            TextView usernameTextView = (TextView) findViewById(R.id.user_username);

            TextView emailTextView = (TextView) findViewById(R.id.user_email);

            TextView firstNameTextView = (TextView) findViewById(R.id.user_first_name);

            TextView lastNameTextView = (TextView) findViewById(R.id.user_last_name);


            HashMap<String, String> user = login.getUserDetails();

            // name
            String username = user.get(SessionManager.KEY_USERNAME);
            // email
            String email = user.get(SessionManager.KEY_EMAIL);
            //first name
            String lastname = user.get(SessionManager.KEY_LASTNAME);
            //last name
            String firstname = user.get(SessionManager.KEY_FIRSTNAME);


            // displaying user data
            usernameTextView.setText(Html.fromHtml("Username: <b>" + username + "</b>"));

            emailTextView.setText(Html.fromHtml("Email: <b>" + email + "</b>"));

            firstNameTextView.setText(Html.fromHtml("Firstname: <b>" + firstname + "</b>"));

            lastNameTextView.setText(Html.fromHtml("LastName: <b>" + lastname + "</b>"));

            new GetFavorites().execute();


//            HashMap<String, String> favoritesMap = login.getUserFavorites();
//
//            String favorites = favoritesMap.get(SessionManager.KEY_FAVORITES);
//
//            JSONArray jObject = new JSONArray(favorites);
//            for (int i = 0; i < jObject.length(); i++,start++) {
//                JSONObject menuObject = jObject.getJSONObject(i);
//
//                String name= menuObject.getString("name");
//                String email= menuObject.getString("email");
//                String image= menuObject.getString("image");
//            }



        }


    }

    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        // Respond to the action bar's Up/Home button
        case android.R.id.home:
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
