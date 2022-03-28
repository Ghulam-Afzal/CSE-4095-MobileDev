package com.example.k2022_03_08_rv

import android.view.InflateException
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.k2022_03_08_rv.model.RadioStation
import com.example.k2022_03_08_rv.model.RadioStations
import java.util.zip.Inflater


lateinit var allStations : MutableList<RadioStation>

class RadioAdapter(var radioStations: RadioStations) : RecyclerView.Adapter<RadioAdapter.RadioViewHolder> () {

    init {
        allStations = radioStations.getStations()
    }

    class RadioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        var name : TextView = itemView.findViewById(R.id.name_text)
        var uri : TextView = itemView.findViewById(R.id.uri_text)
        var whichCard: Int = 0

        fun bind(position: Int) {
            name.text = allStations[position].name
            uri.text = allStations[position].uri

            whichCard = position
        }

        override fun onClick(p0: View?) {
            Toast.makeText(p0?.context, "Hello: " + whichCard.toString(), Toast.LENGTH_LONG).show()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RadioViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.radio_card, parent, false)

         return RadioViewHolder(view)
    }

    override fun onBindViewHolder(holder: RadioViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return allStations.size
    }

}