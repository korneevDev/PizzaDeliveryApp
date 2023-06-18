package github.mik0war.pizzadeliveryapp.feature.advertising.presentation

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import github.mik0war.pizzadeliveryapp.R

class AdvertisingRecyclerViewAdapter(
    private val advertisingList: List<Drawable>
) : RecyclerView.Adapter<AdvertisingViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdvertisingViewHolder =
        AdvertisingViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.advertising_item, parent, false)
        )

    override fun getItemCount() = advertisingList.size

    override fun onBindViewHolder(holder: AdvertisingViewHolder, position: Int) {
        holder.bind(advertisingList[position])
    }
}

class AdvertisingViewHolder(view: View) : ViewHolder(view) {
    fun bind(image: Drawable) {
        itemView.findViewById<ImageView>(R.id.advertisingObject).setImageDrawable(image)
    }
}