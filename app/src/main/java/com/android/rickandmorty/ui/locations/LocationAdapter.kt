package com.android.rickandmorty.ui.locations

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.rickandmorty.R
import com.android.rickandmorty.inflate
import kotlinx.android.synthetic.main.item_location.view.*

class LocationAdapter(private val locations: List<Location>) :
    RecyclerView.Adapter<LocationAdapter.LocationViewHolder>() {

    class LocationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(location: Location) = with(itemView) {
            location_name.text = location.name
            location_dimension.text = location.dimension
            location_type.text = location.type
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder =
        LocationViewHolder(parent.inflate(R.layout.item_location))

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) =
        holder.bind(locations[position])

    override fun getItemCount(): Int =
        locations.size
}
