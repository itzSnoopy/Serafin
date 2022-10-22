package com.example.serafin

import com.google.android.gms.maps.model.LatLng

public class GasStation {
    val name: String
    var location: LatLng

    constructor(name: String, location: LatLng){
        this.name = name
        this.location = location
    }
}