package github.mik0war.pizzadeliveryapp.core

import android.widget.ImageView
import github.mik0war.pizzadeliveryapp.dish.domain.DishMapper


interface UIEntity {
    fun equalsId(other: UIEntity): Boolean
    fun getEntityId(): String
    fun loadImage(imageLoader: ImageLoader, imageView: ImageView)

    fun <T> map(mapper: DishMapper<T>)
}