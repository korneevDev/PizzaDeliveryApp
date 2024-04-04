package github.mik0war.pizzadeliveryapp.dish.domain

data class Ingredient(
    private val name: String,
    private val measure: String
) {
    fun addToDescription(description: String) =
        description + (if (description.isNotEmpty()) ", " else "") + name

    fun <T> map(ingredientMapper: IngredientMapper<T>, dishId: String) =
        ingredientMapper.map(dishId, name, measure)
}