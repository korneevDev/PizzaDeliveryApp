package github.mik0war.pizzadeliveryapp.feature.dish.domain

interface IngredientMapper<T> {

    fun map(dishId: String, name: String, measure: String): T
}