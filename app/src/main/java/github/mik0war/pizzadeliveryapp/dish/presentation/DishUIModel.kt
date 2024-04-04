package github.mik0war.pizzadeliveryapp.dish.presentation

import android.widget.ImageView
import github.mik0war.pizzadeliveryapp.core.ImageLoader
import github.mik0war.pizzadeliveryapp.core.UIEntity
import github.mik0war.pizzadeliveryapp.dish.domain.DishMapper
import github.mik0war.pizzadeliveryapp.dish.domain.Ingredient

sealed interface DishUIModel : UIEntity{
    data class Base(
        private val id: String,
        private val dishName: String,
        private val tag: String,
        private val imageURL: String,
        private val ingredients: List<Ingredient>
    ) : DishUIModel {
        override fun equalsId(other: UIEntity): Boolean =
            other is Base && other.id == id

        override fun getEntityId(): String = id
        override fun loadImage(imageLoader: ImageLoader, imageView: ImageView) {
            imageLoader.loadImage(imageURL, imageView)
        }

        override fun <T> map(mapper: DishMapper<T>) {
            mapper.mapSuccess(id, dishName, tag, imageURL, ingredients)
        }
    }

    data class Error(
        private val errorMessage: String,
        private val errorImageRes: Int
    ) : DishUIModel{
        override fun equalsId(other: UIEntity): Boolean =
            other is Error && other.errorMessage == errorMessage


        override fun getEntityId(): String = errorMessage

        override fun loadImage(imageLoader: ImageLoader, imageView: ImageView) {
            imageView.setImageResource(errorImageRes)
        }

        override fun <T> map(mapper: DishMapper<T>) {
            mapper.mapError(errorMessage, errorImageRes)
        }

    }
    data class Extended(
        private val id: String,
        private val area: String,
        private val instructions: String,
        private val youtubeLink: String,
        private val sourceLink: String,
    ) : DishUIModel {
        override fun equalsId(other: UIEntity) =
            other is Extended && other.id == id

        override fun getEntityId(): String = id

        override fun loadImage(imageLoader: ImageLoader, imageView: ImageView) {}

        override fun <T> map(mapper: DishMapper<T>) {
            mapper.mapExtendedData(id, area, instructions, youtubeLink, sourceLink)
        }
    }
}