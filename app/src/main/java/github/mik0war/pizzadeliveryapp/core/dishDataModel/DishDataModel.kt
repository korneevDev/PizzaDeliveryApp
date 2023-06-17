package github.mik0war.pizzadeliveryapp.core.dishDataModel

class DishDataModel(
    id: Int,
    name: String,
    description: String,
    price: Int,
    imageUrl: String
) : DishEntity.Success(id, name, description, price, imageUrl)
