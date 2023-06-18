package github.mik0war.pizzadeliveryapp.feature.dish.dishDataModel

import github.mik0war.entity.CustomTextViewInterface
import github.mik0war.pizzadeliveryapp.core.CustomTextView
import github.mik0war.pizzadeliveryapp.core.UIEntity
import github.mik0war.pizzadeliveryapp.feature.dish.presentation.PriceFormatter

sealed interface DishUIModel : UIEntity<DishUIModel> {
    override fun equalsId(other: DishUIModel) = false

    class Success(
        id: Int,
        name: String,
        description: String,
        price: Int,
        imageUrl: String
    ) : DishEntity.Success(id, name, description, price, imageUrl), DishUIModel {

        override fun equalsId(other: DishUIModel)
            = other is Success && other.id == this.id

        override fun show(nameView: CustomTextView) {
            nameView.set(name)
        }

        fun getCurrentPrice() = price

        fun show(
            priceFormatter: PriceFormatter,
            priceView: CustomTextViewInterface,
            descriptionView: CustomTextViewInterface
        ) {
            descriptionView.set(description)
            priceView.set(priceFormatter.formatPrice(price))
        }

        override fun getUrl() = imageUrl
    }


    class Error(
        failureMessage: String
    ): DishEntity.Error(failureMessage), DishUIModel {
        override fun getUrl(): String = throw IllegalStateException()

        override fun show(nameView: CustomTextView) {
            nameView.set(name)
        }
    }
}