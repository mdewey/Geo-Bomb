package net.problemsolutions.geobomb;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class GeoBombActivity extends Activity {
    /** Called when the activity is first created. */
	LocationManager mlocationManager;
	LocationListener mlocationListener;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mlocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mlocationListener = new MyLocationListener();
        //NOTE: this is only providing course location based on the network provider, could not get a GPS Signal indoors, look into later.......
        mlocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER  , 0, 0, mlocationListener);
        setDebugLabelText("waiting for location....");
    }
	
	public void setDebugLabelText(String txt)
	{
        TextView mylocationTextView = (TextView) findViewById(R.id.userLocation);
		mylocationTextView.setText(txt);
	}
	
	/***My Location Listener Class**/
	public class MyLocationListener implements LocationListener
	{

		@Override
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub
			String myloc = "lat:" + location.getLatitude() + "| long:" + location.getLongitude();
			setDebugLabelText(myloc);
			Toast.makeText( getApplicationContext(),"Found",Toast.LENGTH_SHORT ).show();
		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			Toast.makeText( getApplicationContext(),"Gps Disabled",Toast.LENGTH_SHORT ).show();
		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			Toast.makeText( getApplicationContext(),"Gps Enabled",Toast.LENGTH_SHORT ).show();
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			Toast.makeText( getApplicationContext(),"Status Changed",Toast.LENGTH_SHORT ).show();
		}
		
	}
	
	
}