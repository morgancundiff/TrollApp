package com.troll.trollmaps;

import java.util.ArrayList;
import org.w3c.dom.Document;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MainActivity extends FragmentActivity implements
        GooglePlayServicesClient.ConnectionCallbacks,
        GooglePlayServicesClient.OnConnectionFailedListener,
        com.google.android.gms.location.LocationListener,
        LocationListener {

	private GoogleMap map;	
	int currentMapZoom;

    // A request to connect to Location Services
    private LocationRequest mLocationRequest;

    // Stores the current instantiation of the location client in this object
    private LocationClient mLocationClient;
    private Location mCurrentLocation;

    // Handle to SharedPreferences for this app
    SharedPreferences mPrefs;

    // Handle to a SharedPreferences editor
    SharedPreferences.Editor mEditor;

    //Starts out as false, to check if the user's preferences are off
    boolean mUpdatesRequested;
    
    //Navigation status
    boolean navigationStatus;
    
    protected LocationManager locationManager;
    private Criteria myCriteria;
    
    GMapDirection mapDirections;
    LatLng warrenLatLng;
    
    @SuppressLint("NewApi")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		setUpMapIfNeeded();

        // Create a new global location parameters object
        mLocationRequest = LocationRequest.create();

        //Set the update interval
        mLocationRequest.setInterval(LocationUtils.UPDATE_INTERVAL_IN_MILLISECONDS);

        // Use high accuracy
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        // Set the interval ceiling to one minute
        mLocationRequest.setFastestInterval(LocationUtils.FAST_INTERVAL_CEILING_IN_MILLISECONDS);

        // Note that location updates are off until the user turns them on
        mUpdatesRequested = false;

        // Open Shared Preferences
        mPrefs = getSharedPreferences(LocationUtils.SHARED_PREFERENCES, Context.MODE_PRIVATE);

        // Get an editor
        mEditor = mPrefs.edit();

        //Create a new location client, using the enclosing class to handle callback
        mLocationClient = new LocationClient(this, this, this);
        
        
        myCriteria = new Criteria();
        myCriteria.setAccuracy(Criteria.ACCURACY_FINE);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(0L,0.0f, myCriteria, this, null);
        
        mapDirections = new GMapDirection();
    }
    
    @SuppressLint("NewApi")
	private void setUpMapIfNeeded() {
	 	// Do a null check to confirm that we have not already instantiated the map.
	    if (map == null) {
	        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
	    }
        // Check if we were successful in obtaining the map.
        if (map != null) {
            // The Map is verified. It is now safe to manipulate the map.

	        map.setMyLocationEnabled(true);
	        
	        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//	        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
//	        map.setMapType(GoogleMap.MAP_TYPE_NONE);
//	        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
//	        map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
	        //QUERY THE DATABASE FOR OPEN OR CLOSED CARTS
			
	        currentMapZoom = 15;

	        //CREATE THE MARKERS WITH THE APPROPRIATE IMAGES
			LatLng ucsdLatLng = new LatLng(32.881271, -117.2389000);//879271,2289000
			//LatLng 
			warrenLatLng = new LatLng(32.880966, -117.234450);
			LatLng centerLatLng = new LatLng(32.878053, -117.237218);
			LatLng socialScienceLatLng = new LatLng(32.883822, -117.240748);
			LatLng SOMLatLng = new LatLng(32.875852, -117.237578);
			LatLng revellePlazaLatLng = new LatLng(32.874851, -117.241217);
			Marker ucsd = map.addMarker(new MarkerOptions().position(ucsdLatLng)
					.visible(false).title("UCSD"));
			Marker warrenMarker = map.addMarker(new MarkerOptions().position(warrenLatLng)
			        .title("Warren").snippet("Welcome to warren coffee cart"));
			Marker centerHallMarker = map.addMarker(new MarkerOptions().position(centerLatLng)
			        .title("Center"));
			Marker socialScienceMarker = map.addMarker(new MarkerOptions().position(socialScienceLatLng)
			        .title("Social Sceinces"));
			Marker SOMMarker = map.addMarker(new MarkerOptions().position(SOMLatLng)
			        .title("SOM"));
			Marker revellePlazaMarker = map.addMarker(new MarkerOptions().position(revellePlazaLatLng)
			        .title("Revelle Plaza"));
			map.moveCamera(CameraUpdateFactory.newLatLngZoom(ucsdLatLng, currentMapZoom));

	        //ADD THE MARKERS TO THE ARRAY LIST
//			mapLocMarker UCSD = new mapLocMarker("UCSD", new LatLng(32.881271, -117.2389000), ucsd);
//			mapLocMarkerArray.add(UCSD);
//			mapLocMarker WARREN = new mapLocMarker("Warren", warrenLatLng, warrenMarker);
//			mapLocMarkerArray.add(WARREN);
//			mapLocMarker UCSD = new mapLocMarker("UCSD", ucsdLatLng, ucsd);
//			mapLocMarkerArray.add(UCSD);
//			mapLocMarker UCSD = new mapLocMarker("UCSD", ucsdLatLng, ucsd);
//			mapLocMarkerArray.add(UCSD);
//			mapLocMarker UCSD = new mapLocMarker("UCSD", ucsdLatLng, ucsd);
//			mapLocMarkerArray.add(UCSD);
//			mapLocMarker UCSD = new mapLocMarker("UCSD", ucsdLatLng, ucsd);
//			mapLocMarkerArray.add(UCSD);
	        //ADD THE MARKERS TO THE MAP
	    }
	}

    //Called when the Activity is no longer visible at all.
    @Override
    public void onStop() {
        // If the client is connected
        if (mLocationClient.isConnected()) {
            stopPeriodicUpdates();		//AHMED:CRASHES ON FLIP
        }
        // After disconnect() is called, the client is considered "dead".
        mLocationClient.disconnect();
        super.onStop();
        Toast.makeText(this, "Stop", Toast.LENGTH_SHORT).show();
    }
    /*
     * Called when the Activity is going into the background.
     * Parts of the UI may be visible, but the Activity is inactive.
     */
    @Override
    public void onPause() {
        // Save the current setting for updates
        mEditor.putBoolean(LocationUtils.KEY_UPDATES_REQUESTED, mUpdatesRequested);
        mEditor.commit();
        super.onPause();
        Toast.makeText(this, "Pause", Toast.LENGTH_SHORT).show();
    }

    /*
     * Called when the Activity is restarted, even before it becomes visible.
     */
    @Override
    public void onStart() {
        super.onStart();
        /*
         * Connect the client. Don't re-start any requests here;
         * instead, wait for onResume()
         */
        mLocationClient.connect();
        
        Toast.makeText(this, "Start", Toast.LENGTH_SHORT).show();
    }
    /*
     * Called when the system detects that this Activity is now visible.
     */
    @Override
    public void onResume() {
        setUpMapIfNeeded();
        super.onResume();
        Toast.makeText(this, "Resume", Toast.LENGTH_SHORT).show();
        // If the app already has a setting for getting location updates, get it
        if (mPrefs.contains(LocationUtils.KEY_UPDATES_REQUESTED)) {
            mUpdatesRequested = mPrefs.getBoolean(LocationUtils.KEY_UPDATES_REQUESTED, false);
//	        if(mUpdatesRequested == true)
//	        	Toast.makeText(this, "True", Toast.LENGTH_SHORT).show();
//	        else
//	        	Toast.makeText(this, "False", Toast.LENGTH_SHORT).show();

        // Otherwise, turn off location updates until requested
        } else {
            mEditor.putBoolean(LocationUtils.KEY_UPDATES_REQUESTED, false);
            mEditor.commit();
        }
        
    }

    /*
     * Handle results returned to this Activity by other Activities started with
     * startActivityForResult(). In particular, the method onConnectionFailed() in
     * LocationUpdateRemover and LocationUpdateRequester may call startResolutionForResult() to
     * start an Activity that handles Google Play services problems. The result of this
     * call returns here, to onActivityResult.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

        // Choose what to do based on the request code
        switch (requestCode) {

            // If the request code matches the code sent in onConnectionFailed
            case LocationUtils.CONNECTION_FAILURE_RESOLUTION_REQUEST :

                switch (resultCode) {
                    // If Google Play services resolved the problem
                    case Activity.RESULT_OK:

                        // Log the result
                        Log.d(LocationUtils.APPTAG, getString(R.string.resolved));
                    break;

                    // If any other result was returned by Google Play services
                    default:
                        // Log the result
                        Log.d(LocationUtils.APPTAG, getString(R.string.no_resolution));
                    break;
                }

            // If any other request code was received
            default:
               // Report that this Activity received an unknown requestCode
               Log.d(LocationUtils.APPTAG,
                       getString(R.string.unknown_activity_request_code, requestCode));

               break;
        }
    }

    /**
     * Verify that Google Play services is available before making a request.
     *
     * @return true if Google Play services is available, otherwise false
     */
    private boolean servicesConnected() {

        // Check that Google Play services is available
        int resultCode =
                GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);

        // If Google Play services is available
        if (ConnectionResult.SUCCESS == resultCode) {
            // In debug mode, log the status
            Log.d(LocationUtils.APPTAG, getString(R.string.play_services_available));

            // Continue
            return true;
        // Google Play services was not available for some reason
        } else {
            // Display an error dialog
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(resultCode, this, 0);
            if (dialog != null) {
                ErrorDialogFragment errorFragment = new ErrorDialogFragment();
                errorFragment.setDialog(dialog);
                errorFragment.show(getSupportFragmentManager(), LocationUtils.APPTAG);
            }
            return false;
        }
    }

    /**
     * Invoked by the "Get Location" button.
     *
     * Calls getLastLocation() to get the current location
     *
     * @param v The view object associated with this method, in this case a Button.
     */
    public void getLocation(View v) {

        // If Google Play Services is available
        if (servicesConnected()) {

            // Get the current location
        	mCurrentLocation = mLocationClient.getLastLocation();
        	Toast.makeText(this, "My Location Button", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * Invoked by the "Start Updates" button
     * Sends a request to start location updates
     *
     * @param v The view object associated with this method, in this case a Button.
     */
    public void startUpdates(View v) {
        mUpdatesRequested = true;

        if (servicesConnected()) {
            startPeriodicUpdates();
        }
    }

    /**
     * Invoked by the "Stop Updates" button
     * Sends a request to remove location updates
     * request them.
     *
     * @param v The view object associated with this method, in this case a Button.
     */
    public void stopUpdates(View v) {
        mUpdatesRequested = false;

        if (servicesConnected()) {
            stopPeriodicUpdates();
        }
    }

    /*
     * Called by Location Services when the request to connect the
     * client finishes successfully. At this point, you can
     * request the current location or start periodic updates
     */
    @Override
    public void onConnected(Bundle bundle) {
        Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();	
        if (mUpdatesRequested) {
            startPeriodicUpdates();
        }
//        mCurrentLocation = mLocationClient.getLastLocation();        
//        String msg = "Connected Location: " +
//                Double.toString(mCurrentLocation.getLatitude()) + "," +
//                Double.toString(mCurrentLocation.getLongitude());
//        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();	
    
    }

    /*
     * Called by Location Services if the connection to the
     * location client drops because of an error.
     */
    @Override
    public void onDisconnected() {
        Toast.makeText(this, "Disonnected", Toast.LENGTH_SHORT).show();	
        stopPeriodicUpdates();//POSSIBLE FAILURE: AHMED
    }

    /*
     * Called by Location Services if the attempt to
     * Location Services fails.
     */
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

        /*
         * Google Play services can resolve some errors it detects.
         * If the error has a resolution, try sending an Intent to
         * start a Google Play services activity that can resolve
         * error.
         */
        if (connectionResult.hasResolution()) {
            try {

                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(
                        this,
                        LocationUtils.CONNECTION_FAILURE_RESOLUTION_REQUEST);

                /*
                * Thrown if Google Play services canceled the original
                * PendingIntent
                */

            } catch (IntentSender.SendIntentException e) {

                // Log the error
                e.printStackTrace();
            }
        } else {

            // If no resolution is available, display a dialog to the user with the error.
            showErrorDialog(connectionResult.getErrorCode());
        }
    }

    /**
     * Report location updates to the UI.
     *
     * @param location The updated location.
     */
    @Override
    public void onLocationChanged(Location location) {

        // Report to the UI that the location was updated
        Toast.makeText(this, "Location Updated", Toast.LENGTH_SHORT).show();	
        // In the UI, set the latitude and longitude to the value received
    	mCurrentLocation = location;
		Document doc = mapDirections.getDocument(new LatLng(location.getLatitude(), location.getLongitude())
		, warrenLatLng, GMapDirection.MODE_WALKING);
//		int duration = mapDirections.getDurationValue(doc);
//		String distance = mapDirections.getDistanceText(doc);
//		String start_address = mapDirections.getStartAddress(doc);
//		String copy_right = mapDirections.getCopyRights(doc);
//		ArrayList<LatLng> directionPoint = mapDirections.getDirection(doc);
//		PolylineOptions rectLine = new PolylineOptions().width(3).color(Color.RED);
//		for(int i=0, _i=directionPoint.size();i<_i;++i)
//		{
//			rectLine.add(directionPoint.get(i));
//		}
//		map.clear();
//		map.addMarker(new MarkerOptions().position(warrenLatLng)
//		        .title("Warren"));
//		map.addPolyline(rectLine);
    	CameraPosition cameraPosition = new CameraPosition.Builder()
    			.target(new LatLng(location.getLatitude(), location.getLongitude())) // Sets the center of the map to
    	        .zoom(currentMapZoom)                   // Sets the zoom
    	        .bearing(0)//(float) myBearing) // Sets the orientation of the camera to east
    	        .tilt(30)//(float)myAngle)    // Sets the tilt of the camera to 30 degrees
    	        .build();    // Creates a CameraPosition from the builder
    	    	map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

//    	locationManager.removeUpdates(this);
    }

    /**
     * In response to a request to start updates, send a request
     * to Location Services
     */
    private void startPeriodicUpdates() {

        mLocationClient.requestLocationUpdates(mLocationRequest, this);
        locationManager.requestLocationUpdates(0L,0.0f, myCriteria, this, null);
        Toast.makeText(this, "Location Updated Requested", Toast.LENGTH_SHORT).show();	
    }

    /**
     * In response to a request to stop updates, send a request to
     * Location Services
     */
    private void stopPeriodicUpdates() {
    	if(mLocationClient != null)
    		mLocationClient.removeLocationUpdates(this);
    	if(locationManager != null)
    		locationManager.removeUpdates(this);

    	else
    		Log.d("Main Activity", "MO: mLocatoinClinet is null at stop");
        Toast.makeText(this, "Location Update Stopped", Toast.LENGTH_SHORT).show();	
    }

    /**
     * Show a dialog returned by Google Play services for the
     * connection error code
     *
     * @param errorCode An error code returned from onConnectionFailed
     */
    private void showErrorDialog(int errorCode) {

        // Get the error dialog from Google Play services
        Dialog errorDialog = GooglePlayServicesUtil.getErrorDialog(
            errorCode,
            this,
            LocationUtils.CONNECTION_FAILURE_RESOLUTION_REQUEST);

        // If Google Play services can provide an error dialog
        if (errorDialog != null) {

            // Create a new DialogFragment in which to show the error dialog
            ErrorDialogFragment errorFragment = new ErrorDialogFragment();

            // Set the dialog in the DialogFragment
            errorFragment.setDialog(errorDialog);

            // Show the error dialog in the DialogFragment
            errorFragment.show(getSupportFragmentManager(), LocationUtils.APPTAG);
        }
    }

    /**
     * Define a DialogFragment to display the error dialog generated in
     * showErrorDialog.
     */
    public static class ErrorDialogFragment extends DialogFragment {

        // Global field to contain the error dialog
        private Dialog mDialog;

        /**
         * Default constructor. Sets the dialog field to null
         */
        public ErrorDialogFragment() {
            super();
            mDialog = null;
        }

        /**
         * Set the dialog to display
         *
         * @param dialog An error dialog
         */
        public void setDialog(Dialog dialog) {
            mDialog = dialog;
        }

        /*
         * This method must return a Dialog to the DialogFragment.
         */
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return mDialog;
        }
    }

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}
}
