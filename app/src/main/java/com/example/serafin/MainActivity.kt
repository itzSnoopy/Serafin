package com.example.serafin

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationRequest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.app.ActivityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.serafin.databinding.ActivityMainBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.OnTokenCanceledListener
import com.maps.route.extensions.drawRouteOnMap
import com.maps.route.extensions.moveCameraOnMap

class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMainBinding
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var currentLocation: LatLng

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*
            Navigation Menu
        */
        val drawerLayout : DrawerLayout = findViewById(R.id.drawerLayout)
        val navView : NavigationView = findViewById(R.id.nav_view)
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener{
            var newActivity: Intent? = null

            when(it.itemId){
                R.id.nav_home -> {
                    newActivity = Intent(this,MainActivity::class.java)
                    Toast.makeText(applicationContext, R.string.nav_home, Toast.LENGTH_LONG).show()
                }
                R.id.nav_settings -> {
                    newActivity = Intent(this, SettingsActivity::class.java)
                    Toast.makeText(applicationContext, R.string.nav_settings, Toast.LENGTH_LONG).show()
                }
                R.id.nav_logout -> {
                    newActivity = Intent(this,LoginActivity::class.java)
                    Toast.makeText(applicationContext, R.string.nav_logout, Toast.LENGTH_LONG).show()
                }
            }

            if(newActivity != null){
                startActivity(newActivity)
            }

            true
        }

        /*
            GPS Location Support
        */
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            val requestPermissionLauncher =
                registerForActivityResult(ActivityResultContracts.RequestPermission())
                { isGranted: Boolean ->
                    if(isGranted){

                    } else {

                    }
                }

            requestPermissionLauncher.launch(
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            requestPermissionLauncher.launch(
                Manifest.permission.ACCESS_COARSE_LOCATION
            )

            return
        }
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationClient.getCurrentLocation(LocationRequest.QUALITY_HIGH_ACCURACY, object: CancellationToken(){
            override fun onCanceledRequested(p0: OnTokenCanceledListener): CancellationToken = CancellationTokenSource().token
            override fun isCancellationRequested(): Boolean = false
        })
            .addOnSuccessListener { location: Location? ->
                if(location == null)
                    Toast.makeText(this, "Cannot get current location", Toast.LENGTH_SHORT).show()
                else
                    currentLocation = LatLng(location.latitude, location.latitude)
            }


        /*
            Maps Fragment
        */
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap){
        mMap = googleMap

        val startPoint = LatLng(-12.0771901,-77.0932182)
        val gasStations: HashMap<Int, GasStation> = Constants.getKnownGasStations()
        for (station in gasStations.values){
            mMap.addMarker(MarkerOptions()
                .position(station.location)
                .title(station.name)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.gas_banner))
            )
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLng(startPoint))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(startPoint, 13.0f))
        mMap.setOnMarkerClickListener { marker ->
            var destination: LatLng = marker.position
            var source: LatLng = LatLng(-12.0653494,-77.1192116) //currentLocation

            googleMap.run {
                moveCameraOnMap(latLng = source)

                drawRouteOnMap(
                    getString(R.string.api_key),
                    source = source,
                    destination = destination,
                    context = this@MainActivity
                )
            }

            if (marker.isInfoWindowShown) {
                marker.hideInfoWindow()
            } else {
                // marker.showInfoWindow()
            }

            true
        }
    }

    private val locationListener: LocationListener = object: LocationListener {
        override fun onLocationChanged(location: Location) {
            currentLocation = LatLng(location.latitude, location.longitude)
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) { }
        override fun onProviderEnabled(provider: String) { }
        override fun onProviderDisabled(provider: String) { }
    }
}