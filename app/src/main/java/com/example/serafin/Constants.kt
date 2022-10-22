package com.example.serafin

import com.google.android.gms.maps.model.LatLng

public class Constants {

    companion object{
        public fun getKnownGasStations(): HashMap<Int, GasStation> {
            val gasStations = hashMapOf<Int, GasStation>()
            gasStations[0] = GasStation("Primax - El Carmelo", LatLng(-12.0754955, -77.0969767))
            gasStations[1] = GasStation("Grifo Gazel", LatLng(-12.0770745, -77.0948177))
            gasStations[2] = GasStation("Primax Service Station", LatLng(-12.0782804, -77.0842149))


            return gasStations
        }


    }
}