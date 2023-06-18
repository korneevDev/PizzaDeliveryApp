package github.mik0war.pizzadeliveryapp.feature.dish.dishDataModel

import github.mik0war.pizzadeliveryapp.core.Entity

sealed interface DishModel : Entity {
    class Success(
        id: Int,
        name: String,
        description: String,
        price: Int,
        imageUrl: String
    ) : DishEntity.Success(id, name, description, price, imageUrl), DishModel

    class Error(message: String): DishEntity.Error(name=message), DishModel
}
