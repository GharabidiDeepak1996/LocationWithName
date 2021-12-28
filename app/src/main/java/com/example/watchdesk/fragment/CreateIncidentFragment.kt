package com.example.watchdesk.fragment


import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker

import com.example.watchdesk.R
import com.example.watchdesk.databinding.FragmentCreateIncidentBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

import java.io.IOException
import java.util.*


class CreateIncidentFragment : Fragment(){
    private val TAG = "CreateIncidentFragment"

    private lateinit var bindingCreateIncidentBinding: FragmentCreateIncidentBinding
    private lateinit var supportMapFragment: SupportMapFragment //Initialize variable



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingCreateIncidentBinding = FragmentCreateIncidentBinding.inflate(inflater, container, false)


        //Assign variable
        // val fragmentManager = activity?.supportFragmentManager
        supportMapFragment = childFragmentManager.findFragmentById(R.id.map_view) as SupportMapFragment


        bindingCreateIncidentBinding.fromText.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(char: CharSequence?, p1: Int, p2: Int, p3: Int) {
               getLatLngFromLocationAddress(char.toString())
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
        return bindingCreateIncidentBinding.root
    }


    //get address for FROM
    private fun getLatLngFromLocationAddress(locationName: String){
        val geocoder= Geocoder(context,Locale.getDefault())
        try {
            val addressList=geocoder.getFromLocationName(locationName,1)
            if(addressList!=null && addressList.size>0){
                val address= addressList.get(0) as Address
                getCurrentLocation(address.latitude,address.longitude)
            }
        }catch (e: IOException){
            e.printStackTrace()
        }

    }
    private fun getCurrentLocation(latitude: Double,longitude: Double) {
        supportMapFragment.getMapAsync(object : OnMapReadyCallback {
            override fun onMapReady(googleMap: GoogleMap) {

                //Initialize lat lng
                val latLng = LatLng(latitude, longitude)

                //create marker option
                val makerOption = MarkerOptions().position(latLng).snippet("snippet").title("Start Point").icon(
                    BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                //BitmapDescriptorFactory.fromResource(R.drawable.item1) for image

                //zoom map
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18F))

                //Add marker on map
                googleMap.addMarker(makerOption)
            }

        })
    }


}