package github.mik0war.pizzadeliveryapp.feature.dish.domain


interface DishMapper<T> {
    fun mapSuccess(id: String,
                   dishName: String,
                   tag: String,
                   imageURL: String,
                   ingredients: List<Ingredient>): T
    fun mapError(errorName: String, errorImageId: Int): T

    fun mapExtendedData(id: String,
            area: String,
            instructions: String,
            youtubeLink: String,
            sourceLink: String,) : T
}


