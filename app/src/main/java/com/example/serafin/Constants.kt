package com.example.serafin

import com.google.android.gms.maps.model.LatLng

public class Constants {

    companion object{
        public fun getKnownGasStations(): HashMap<Int, GasStation> {
            val gasStations = hashMapOf<Int, GasStation>()
            gasStations[0] = GasStation("Primax - El Carmelo", LatLng(-12.0754955, -77.0969767))
            gasStations[1] = GasStation("Grifo Gazel", LatLng(-12.0770745, -77.0948177))
            gasStations[2] = GasStation("Primax Service Station", LatLng(-12.0782804, -77.0842149))
            gasStations[3] = GasStation("GAZEL - THE OVAL", LatLng(-12.0661163, -77.1190467))
            gasStations[4] = GasStation("Primax La Perla", LatLng(-12.0758591, -77.1147618))
            gasStations[5] = GasStation("Primax Escardó", LatLng(-12.0807188, -77.0947913))
            gasStations[6] = GasStation("Estación de Servicio Repsol", LatLng(-12.065011, -77.097412))

            return gasStations
        }


    }
}