package com.iamkaan.orienteering101;

import android.graphics.Point;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.widget.ImageView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    int width;
    ImageView map;
    LocationManager locationManager;
    private GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        map = (ImageView) findViewById(R.id.map);

        width = getScreenWidth();

        buildGoogleApiClient();
    }

    @Override
    protected void onStart() {
        super.onStart();
        googleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (googleApiClient.isConnected()) {
            googleApiClient.disconnect();
        }
    }

    private int getScreenWidth() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    protected synchronized void buildGoogleApiClient() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public void onConnected(Bundle bundle) {

        Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(
                googleApiClient);

        MapCreator mapCreator = new MapCreator()
                .setCenter(lastLocation.getLatitude(), lastLocation.getLongitude())
                .setSize(1000, 1000)
                .setZoom(17);

        mapCreator.addMarker(new Marker()
                .setIconURL("http://www2.psd100.com/icon/2013/08/2701/Running-man-icon-0827112812.png")
                .setLocation(lastLocation.getLatitude(), lastLocation.getLongitude()));

        mapCreator.addMarker(new Marker()
                .setIconURL("http://hydra-media.cursecdn.com/neverwinter.gamepedia.com/9/93/Icon_Inventory_Runestone_Serene_T1_01.png?version=7226ca2069eec585bf3a4d3a88879631")
                .setLocation(39.779014, 30.517056));

        String url = mapCreator
                .create();

        System.out.println(url);

        Picasso.with(this)
                .load(url)
                .error(R.mipmap.ic_launcher)
                .resize(width, width)
                .centerInside()
                .into(map);
    }

    @Override
    public void onConnectionSuspended(int i) {
        System.out.println("ahE");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        System.out.println("ahE");
    }
}