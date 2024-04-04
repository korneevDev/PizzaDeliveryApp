package github.mik0war.pizzadeliveryapp.feature.advertising.presentation

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import github.mik0war.pizzadeliveryapp.databinding.AdvertisingItemBinding

class AdvertisingRecyclerViewAdapter(
    private val advertisingList: List<Drawable>
) : RecyclerView.Adapter<AdvertisingViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdvertisingViewHolder =
        AdvertisingViewHolder(
            AdvertisingItemBinding.inflate(LayoutInflater.from(parent.context))
        )

    override fun getItemCount() = advertisingList.size

    override fun onBindViewHolder(holder: AdvertisingViewHolder, position: Int) {
        holder.bind(advertisingList[position])
    }
}

class AdvertisingViewHolder(private val binding: AdvertisingItemBinding) : ViewHolder(binding.root) {
    fun bind(image: Drawable) {
        binding.advertisingObject.setImageDrawable(image)
    }
}