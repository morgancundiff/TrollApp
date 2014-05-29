package edu.ucsd.troll.app;

/**
 * Created by shalomabitan on 5/28/14.
 */

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
/**
 * This activity will be the single menu item's display
 * page. it will recieve data from the calling activity.
 * it needs the item to be able to make the appropriate
 * additions, such as "add to favorites", "rating", etc
 */
public class SingleMenuItemActivity extends Activity{
	
	
	  private RatingBar ratingBar;
	  private TextView txtRatingValue;
	  private Button btnSubmit;
	 
	  @Override
	  public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_single_item);
		
		addListenerOnRatingBar();
		addListenerOnButton();
	 
	  }
	 
	  public void addListenerOnRatingBar() {
		  
		  String ratingValue = getIntent().getStringExtra("rating");
			
		  Log.d("the value of rating is: " , ratingValue);
	 
		ratingBar = (RatingBar) findViewById(R.id.ratingBar);
		ratingBar.setRating(Float.parseFloat(ratingValue));
		txtRatingValue = (TextView) findViewById(R.id.txtRatingValue);
	 
		//if rating value is changed,
		//display the current rating value in the result (textview) automatically
		ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			public void onRatingChanged(RatingBar ratingBar, float rating,
				boolean fromUser) {
	 
				txtRatingValue.setText(String.valueOf(rating));
	 
			}
		});
	  }
	 
	  public void addListenerOnButton() {
	 
		ratingBar = (RatingBar) findViewById(R.id.ratingBar);
		btnSubmit = (Button) findViewById(R.id.btnSubmit);
	 
		//if click on me, then display the current rating value.
		btnSubmit.setOnClickListener(new OnClickListener() {
	 
			@Override
			public void onClick(View v) {
	 
				Toast.makeText(SingleMenuItemActivity.this,
					String.valueOf(ratingBar.getRating()),
						Toast.LENGTH_SHORT).show();
	 
			}
	 
		});
	 
	  }
	  
	  
	  
}
