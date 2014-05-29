package edu.ucsd.troll.app;

/**
 * Created by shalomabitan on 5/28/14.
 */

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
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
	
	
    private static final String TAG_MENU = "menu";
    private static final String TAG_ID = "id";
    private static final String TAG_TITLE = "title";
    private static final String TAG_DESCRIPTION = "description";
    private static final String TAG_CATEGORY = "category";
    private static final String TAG_RATING = "rating";
    private static final String TAG_VOTES = "total_votes";
    private static final String TAG_SIZES = "sizes";
    private static final String TAG_SIZE = "size";
    private static final String TAG_PRICE = "price";
    
	  private RatingBar ratingBar;
	  private TextView txtRatingValue;
	  private Button btnSubmit;
	 
	  @Override
	  public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_single_item);
		
		TextView itemTitle = (TextView)findViewById(R.id.lblTitle);  
		itemTitle.setText(getIntent().getStringExtra(TAG_TITLE));
		
		TextView itemDescription = (TextView)findViewById(R.id.lblTitle);  
		itemDescription.setText(getIntent().getStringExtra(TAG_DESCRIPTION));
		
		TextView itemCategory = (TextView)findViewById(R.id.lblTitle);  
		itemCategory.setText(getIntent().getStringExtra(TAG_CATEGORY));
		
		
		addListenerOnRatingBar();
		addListenerOnButton();
	 
	  }
	 
	 
	  public void addListenerOnRatingBar() {
		  
		String ratingValue = getIntent().getStringExtra("rating");

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
