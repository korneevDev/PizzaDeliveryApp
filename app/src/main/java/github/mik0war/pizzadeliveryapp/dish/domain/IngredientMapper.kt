package github.mik0war.pizzadeliveryapp.dish.domain

interface IngredientMapper<T> {

    fun map(dishId: String, name: String, measure: String): T
}