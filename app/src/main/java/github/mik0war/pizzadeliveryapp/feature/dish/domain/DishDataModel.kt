package github.mik0war.pizzadeliveryapp.feature.dish.domain

sealed interface DishDataModel {
    fun <T> map(mapper: DishMapper<T>): T
    data class Base(
        private val id: String,
        private val dishName: String,
        private val tag: String,
        private val imageURL: String,
        private val ingredients: List<Ingredient>,
    ) : DishDataModel {
        override fun <T> map(mapper: DishMapper<T>) =
            mapper.mapSuccess(id, dishName, tag, imageURL, ingredients)

        fun getIngredientsList() = ingredients
    }

    data class Extended(
        private val id: String,
        private val area: String,
        private val instructions: String,
        private val youtubeLink: String,
        private val sourceLink: String,
    ) : DishDataModel {
        override fun <T> map(mapper: DishMapper<T>) =
            mapper.mapExtendedData(id, area, instructions, youtubeLink, sourceLink)
    }

    data class Error(
        private val errorMessage: String,
        private val errorImageId: Int
    ) : DishDataModel {
        override fun <T> map(mapper: DishMapper<T>): T =
            mapper.mapError(errorMessage, errorImageId)
    }
}