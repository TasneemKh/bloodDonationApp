package com.example.blooddonationapp;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.blooddonationapp.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.NotNull;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

public class map extends Fragment implements OnMapReadyCallback {
    private MapView mMapView;
    private GoogleMap googleMap;
    GoogleMap mGoogleMap;
    LocationRequest mLocationRequest;
    FusedLocationProviderClient mFusedLocationClient;
    Marker mCurrLocationMarker;
    FirebaseUser user;
    private FirebaseAuth mAuth;
    private static final String TAG = "info";
    GoogleApiClient mGoogleApiClient;
    SettingsClient settingsClient;
    MarkerOptions markerOptions;
    boolean locationPermissionGranted;
    private Location lastKnownLocation;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private CameraPosition cameraPosition;
    private static final String KEY_LOCATION = "location";
    private final LatLng defaultLocation = new LatLng(31.5017, 34.4668);
    SearchView sv_location;
    private double latitude, longitude;
    String hospital = "hospital";
    GetNearbyPlacesData GetNearbyPlacesData = new GetNearbyPlacesData();
    private int ProximityRadius = 10000;
    ProgressDialog progressDialog;
    LatLng origin = new LatLng(31.4309, 34.3729);
    LatLng dest = new LatLng(31.4211, 34.3851);


    public map() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        // Initialize the AutocompleteSupportFragment
        return view;
    }

    @Override
    public void onViewCreated(View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);

        // Construct a PlacesClient
        Places.initialize(getActivity().getApplicationContext(), getString(R.string.api_Key));
        PlacesClient placesClient = Places.createClient(getContext());
        // Construct a FusedLocationProviderClient.
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getContext());

        // Build the map.
        // [START maps_current_place_map_fragment]
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment == null) {
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            mapFragment = SupportMapFragment.newInstance();
            ft.replace(R.id.map, mapFragment);
        }
        mapFragment.getMapAsync(this);
        sv_location = (SearchView) getView().findViewById(R.id.sv_location);
        sv_location.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String location = sv_location.getQuery().toString();
                String location1 = location + " " + "gaza";
                List<Address> addressList = null;
                if (!TextUtils.isEmpty(location)) {
                    Geocoder geocoder = new Geocoder(getContext());
                    try {
                        addressList = geocoder.getFromLocationName(location1, 6);
                        if (addressList != null) {
                            for (int i = 0; i < addressList.size(); i++) {
                                Address address = addressList.get(0);
                                LatLngBounds ADELAIDE = new LatLngBounds
                                        (new LatLng(31.3290, 34.2107),
                                                new LatLng(31.5281, 34.5829));
                                LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                                markerOptions = new MarkerOptions();
                                markerOptions.position(latLng);
                                markerOptions.title(location);
                                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                                mGoogleMap.addMarker(markerOptions);
                                mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                                mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(12));

                            }
                        } else {
                            Toast.makeText(getActivity().getApplicationContext(), "location not found", Toast.LENGTH_SHORT).show();

                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    // mMap.addMarker(new MarkerOptions().position(latLng).title(location));
                    // mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Please, write any location name", Toast.LENGTH_SHORT).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    /*AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
            getChildFragmentManager().findFragmentById(R.id.autocomplete_fragment);

    // Specify the types of place data to return.
    autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));

    // Set up a PlaceSelectionListener to handle the response.
    autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
        @Override
        public void onPlaceSelected(@NotNull Place place) {
            // TODO: Get info about the selected place.
            Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());
        }


        @Override
        public void onError(@NotNull Status status) {
            // TODO: Handle the error.
            Log.i(TAG, "An error occurred: " + status);
        }
    });*/
    /*PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
            getActivity().getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

    autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {

        @Override
        public void onPlaceSelected(Place place) {
            mGoogleMap.clear();
            mGoogleMap.addMarker(new MarkerOptions().position(place.getLatLng()).title(place.getName().toString()));
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(place.getLatLng()));
            mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(place.getLatLng(), 12.0f));
        }

        @Override
        public void onError(Status status) {

        }
    });*/


    }

    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(getContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            locationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        locationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    locationPermissionGranted = true;
                }
            }
        }
        updateLocationUI();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        LatLng gaza = new LatLng(31.5017, 34.4668);
        mGoogleMap.addMarker(new MarkerOptions().position(gaza).title("Title").snippet("Gaza"));
        // For zooming functionality
        CameraPosition cameraPosition = new CameraPosition.Builder().target(gaza).zoom(15).build();
        mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        // [END map_current_place_set_info_window_adapter]

       /* mGoogleMap.getUiSettings().setZoomControlsEnabled(true);
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (getContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mGoogleMap.setMyLocationEnabled(true);
        mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);

        // Show marker on the screen and adjust the zoom level
        mGoogleMap.addMarker(new MarkerOptions().position(origin).title("Origin"));
        mGoogleMap.addMarker(new MarkerOptions().position(dest).title("Destination"));
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(origin,8f));
        new DownloadTask().execute(getDirectionsUrl(origin,dest));*/
        // Prompt the user for permission.
        getLocationPermission();

        // Turn on the My Location layer and the related control on the map.
        updateLocationUI();

        // Get the current location of the device and set the position of the map.
       getDeviceLocation();

    }
    private void updateLocationUI() {
        if (mGoogleMap == null) {
            return;
        }
        try {
            if (locationPermissionGranted) {
                mGoogleMap.setMyLocationEnabled(true);
                mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                mGoogleMap.setMyLocationEnabled(false);
                mGoogleMap.getUiSettings().setMyLocationButtonEnabled(false);
                lastKnownLocation = null;
                getLocationPermission();
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }
    private void getDeviceLocation() {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        try {
            if (locationPermissionGranted) {

                    Task<Location> locationResult = mFusedLocationClient.getLastLocation();
                    locationResult.addOnCompleteListener(getActivity(), new OnCompleteListener<Location>() {
                        @Override
                        public void onComplete(@NonNull Task<Location> task) {
                            if (task.isSuccessful()) {
                                // Set the map's camera position to the current location of the device.
                                lastKnownLocation = task.getResult();
                                if (lastKnownLocation != null) {
                                    mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                            new LatLng(lastKnownLocation.getLatitude(),
                                                    lastKnownLocation.getLongitude()), 15));
                                    LatLng latLng = new LatLng(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude());

                                    MarkerOptions markerOptions = new MarkerOptions();
                                    markerOptions.position(latLng);
                                    markerOptions.title("Current Position");
                                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
                                    mCurrLocationMarker = mGoogleMap.addMarker(markerOptions);
                                    /*String url=getUrl(latitude,longitude,hospital);
                                    Object transferData[]=new Object[2];
                                    transferData[0]=mGoogleMap;
                                    transferData[1]=url;
                                    GetNearbyPlacesData.execute(transferData);
                                    Toast.makeText(getContext(), "Searching for Nearby Hospitals...", Toast.LENGTH_SHORT).show();
                                    Toast.makeText(getContext(), "Showing Nearby Hospitals...", Toast.LENGTH_SHORT).show();
*/
                                }
                            } else {
                                Log.d(TAG, "Current location is null. Using defaults.");
                                Log.e(TAG, "Exception: %s", task.getException());
                                mGoogleMap.moveCamera(CameraUpdateFactory
                                        .newLatLngZoom(defaultLocation, 15));
                                mGoogleMap.getUiSettings().setMyLocationButtonEnabled(false);
                            }
                        }
                    });

            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage(), e);
        }
    }

   /* private String getUrl(double latitude,double longitude,String hospital){
    StringBuilder googleUrl=new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googleUrl.append("location=" + latitude + "," + longitude);
        googleUrl.append("&radius=" + ProximityRadius);
        googleUrl.append("&type=" + hospital);
        googleUrl.append("&sensor=true");
        googleUrl.append("&key=" + "AIzaSyDMCd0ZnC9MEooFSIUmbLuKu6-aXirOuxA");

        Log.d("GoogleMapsActivity", "url = " + googleUrl.toString());

        return googleUrl.toString();
    }*/ /*private void drawPolylines() {
       progressDialog = new ProgressDialog(getContext());
       progressDialog.setMessage("Please Wait, Polyline between two locations is building.");
       progressDialog.setCancelable(false);
       progressDialog.show();

       // Checks, whether start and end locations are captured
       // Getting URL to the Google Directions API
       String url = getDirectionsUrl(origin, dest);
       Log.d("url", url + "");
       DownloadTask downloadTask = new DownloadTask();
       // Start downloading json data from Google Directions API
       downloadTask.execute(url);
   }*/
/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
  /* private class DownloadTask extends AsyncTask<String, Void, String> {

       @Override
       protected String doInBackground(String... url) {

           String data = "";

           try {
               data = downloadUrl(url[0]);
           } catch (Exception e) {
               Log.d("Background Task", e.toString());
           }
           return data;
       }

       @Override
       protected void onPostExecute(String result) {
           super.onPostExecute(result);

           ParserTask parserTask = new ParserTask();


           parserTask.execute(result);

       }
   }

    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

       // Parsing the data in non-ui thread
       @Override
       protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

           JSONObject jObject;
           List<List<HashMap<String, String>>> routes = null;

           try {
               jObject = new JSONObject(jsonData[0]);
               DirectionsJSONParser parser = new DirectionsJSONParser();

               routes = parser.parse(jObject);
           } catch (Exception e) {
               e.printStackTrace();
           }
           return routes;
       }

       @Override
       protected void onPostExecute(List<List<HashMap<String, String>>> result) {

           progressDialog.dismiss();
           Log.d("result", result.toString());
           ArrayList points = null;
           PolylineOptions lineOptions = null;

           for (int i = 0; i < result.size(); i++) {
               points = new ArrayList();
               lineOptions = new PolylineOptions();

               List<HashMap<String, String>> path = result.get(i);

               for (int j = 0; j < path.size(); j++) {
                   HashMap<String, String> point = path.get(j);

                   double lat = Double.parseDouble(point.get("lat"));
                   double lng = Double.parseDouble(point.get("lng"));
                   LatLng position = new LatLng(lat, lng);

                   points.add(position);
               }

               lineOptions.addAll(points);
               lineOptions.width(12);
               lineOptions.color(Color.RED);
               lineOptions.geodesic(true);

           }

// Drawing polyline in the Google Map for the i-th route
           mGoogleMap.addPolyline(lineOptions);
       }
   }

    private String getDirectionsUrl(LatLng origin, LatLng dest) {

        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;

        // Sensor enabled
        String sensor = "sensor=false";
        String mode = "mode=driving";
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor + "&" + mode;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;


        return url;
    }
    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.connect();

            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();

            br.close();
            Log.d("data", data);

        } catch (Exception e) {
            Log.d("Exception", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }
*/

}

