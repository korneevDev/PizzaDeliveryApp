package github.mik0war.pizzadeliveryapp.core

import android.widget.ImageView
import com.squareup.picasso.Picasso
import github.mik0war.pizzadeliveryapp.R
import javax.inject.Inject

interface ImageLoader {
    fun loadImage(imageUrl: String, imageView: ImageView)

    class Base @Inject constructor() : ImageLoader {
        override fun loadImage(imageUrl: String, imageView: ImageView) {
            Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.downloading)
                .into(imageView)
        }
    }
}