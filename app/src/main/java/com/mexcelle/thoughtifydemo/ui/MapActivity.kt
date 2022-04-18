package com.mexcelle.thoughtifydemo.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.mexcelle.thoughtifydemo.R
import com.mexcelle.thoughtifydemo.util.Utility
import kotlinx.android.synthetic.main.activity_location.*
import java.util.*


class MapActivity : AppCompatActivity(), OnMapReadyCallback {


    private lateinit var mMap: GoogleMap
    val TAG = ""
    private val AUTOCOMPLETE_REQUEST_CODE = 1
    val fields = listOf(Place.Field.ID, Place.Field.NAME)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)

        Places.initialize(getApplicationContext(), getString(R.string.api_key), Locale.US);



        Utility.setSolidFontAwesome(this@MapActivity,location_arrow_tv)
        Utility.setRegular(this@MapActivity,location_et)
        Utility.setRegular(this@MapActivity,select_location_tv)
        Utility.setRegular(this@MapActivity,save_txt)



        location_et.setOnClickListener(View.OnClickListener {

            // Start the autocomplete intent.
           /* val intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, fields)
                .build(this@MapActivity)
            startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE)
*/

            val intent = Autocomplete.IntentBuilder(
                AutocompleteActivityMode.FULLSCREEN, fields
            )
                .build(this)
            startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE)

        })


        save_txt.setOnClickListener(View.OnClickListener {

            //finish()
            val intent = Intent()
            Log.e("fields","fields "+fields);
            intent.putExtra("MESSAGE", "Test Location")
            setResult(1, intent)
            finish() //finishing activity

        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            when (resultCode) {
                Activity.RESULT_OK -> {
                    data?.let {
                        val place = Autocomplete.getPlaceFromIntent(data)
                        Log.i(TAG, "Place: ${place.name}, ${place.id}")
                    }
                }
                AutocompleteActivity.RESULT_ERROR -> {
                    // TODO: Handle the error.
                    data?.let {
                        val status = Autocomplete.getStatusFromIntent(data)
                        Log.i(TAG, status.statusMessage ?: "")
                    }
                }
                Activity.RESULT_CANCELED -> {
                    // The user canceled the operation.
                }
            }
            return
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap
        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(
            MarkerOptions()
            .position(sydney)
            .title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))


    }
}