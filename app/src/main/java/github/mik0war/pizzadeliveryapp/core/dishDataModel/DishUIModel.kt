package github.mik0war.pizzadeliveryapp.core.dishDataModel

import github.mik0war.entity.CustomTextView
import github.mik0war.pizzadeliveryapp.core.UIEntity

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