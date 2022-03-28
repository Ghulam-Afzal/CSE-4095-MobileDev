package com.example.k2022_03_08_rv.model

import kotlin.collections.MutableList

var stations: MutableList<RadioStation> = mutableListOf(RadioStation())


class RadioStations() {

    init {
        stations.add(RadioStation("name", "http://stream.whus.org:8000/whusfm"))
        stations.add(RadioStation("Sainte Famille  ", "http://ns385874.ip-176-31-243.eu:8001/stream"))
        stations.add(RadioStation("Maine NPR  ", "https://playerservices.streamtheworld.com/api/livestream-redirect/WMEAFM.mp3"))
        stations.add(RadioStation("Radio Argentia", "http://19253.live.streamtheworld.com:80/LOS40_ARGENTINAAAC_SC"))
    }

    public fun getStations() : MutableList<RadioStation> {

        return stations
    }

    public fun size() : Int {
        return stations.size
    }
}

