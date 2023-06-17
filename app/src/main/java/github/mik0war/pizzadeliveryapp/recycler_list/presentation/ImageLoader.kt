package github.mik0war.recycler_list.presentation

import android.widget.ImageView
import com.squareup.picasso.Picasso
import javax.inject.Inject

interface ImageLoader {
    fun loadImage(imageUrl: String, imageView: ImageView)

    class Base @Inject constructor() : ImageLoader {
        override fun loadImage(imageUrl: String, imageView: ImageView) {
            Picasso.get()
                .load(imageUrl)
                .into(imageView)
        }
    }
}