package com.example.k2022_03_08_rv

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.k2022_03_08_rv.model.RadioStation
import com.example.k2022_03_08_rv.model.RadioStations

import com.example.k2022_03_08_rv.controller.MediaController

var mediaController = MediaController()


lateinit var allStations : MutableList<RadioStation>

class RadioAdapter(var radioStations: RadioStations) : RecyclerView.Adapter<RadioAdapter.RadioViewHolder> () {

    init {
        allStations = radioStations.getStations()
    }

    class RadioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        private var radioStatus: Boolean = false
        private var whichCard: Int = 1
        private var uri: String = ""

        private var name: TextView = itemView.findViewById(R.id.name_text)

        fun bind(position: Int) {
            name.text = allStations[position].name

            whichCard = position
        }

        override fun onClick(p0: View?) {
            uri = allStations[whichCard].uri.toString()
            Log.i("Radio", "Radio link: $uri")

            Toast.makeText(p0?.context, uri, Toast.LENGTH_LONG).show()

            radioStatus = if(radioStatus){
                mediaController.mediaPlayer.pause()
                false
            } else {
                mediaController.setAndPrepareRadioLink(uri)
                true
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RadioViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.radio_card, parent, false)
        return RadioViewHolder(view)

    }

    override fun onBindViewHolder(holder: RadioViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return allStations.size
    }

}