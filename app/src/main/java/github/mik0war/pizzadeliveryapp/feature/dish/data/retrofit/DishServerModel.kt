package github.mik0war.pizzadeliveryapp.feature.dish.data.retrofit

import com.google.gson.annotations.SerializedName
import github.mik0war.pizzadeliveryapp.feature.dish.domain.DishMapper
import github.mik0war.pizzadeliveryapp.feature.dish.domain.Ingredient

data class DishListServerModel(
    @SerializedName("meals")
    private val productList: List<DishServerModel>

) {
    fun getList() = productList
}

data class DishServerModel(
    @SerializedName("idMeal")
    private val id: String,
    @SerializedName("strMeal")
    private val dishName: String,
    @SerializedName("strCategory")
    private val tag: String,
    @SerializedName("strMealThumb")
    private val imageURL: String,
    @SerializedName("strArea")
    private val area: String,
    @SerializedName("strInstructions")
    private val instructions: String,
    @SerializedName("strYoutube")
    private val youtubeLink: String,
    @SerializedName("strSource")
    private val sourceLink: String?,

    @SerializedName("strIngredient1")
    private val strIngredient1: String?,
    @SerializedName("strMeasure1")
    private val strMeasure1: String?,
    @SerializedName("strIngredient2")
    private val strIngredient2: String?,
    @SerializedName("strMeasure2")
    private val strMeasure2: String?,
    @SerializedName("strIngredient3")
    private val strIngredient3: String?,
    @SerializedName("strMeasure3")
    private val strMeasure3: String?,
    @SerializedName("strIngredient4")
    private val strIngredient4: String?,
    @SerializedName("strMeasure4")
    private val strMeasure4: String?,
    @SerializedName("strIngredient5")
    private val strIngredient5: String?,
    @SerializedName("strMeasure5")
    private val strMeasure5: String?,
    @SerializedName("strIngredient6")
    private val strIngredient6: String?,
    @SerializedName("strMeasure6")
    private val strMeasure6: String?,
    @SerializedName("strIngredient7")
    private val strIngredient7: String?,
    @SerializedName("strMeasure7")
    private val strMeasure7: String?,
    @SerializedName("strIngredient8")
    private val strIngredient8: String?,
    @SerializedName("strMeasure8")
    private val strMeasure8: String?,
    @SerializedName("strIngredient9")
    private val strIngredient9: String?,
    @SerializedName("strMeasure9")
    private val strMeasure9: String?,
    @SerializedName("strIngredient10")
    private val strIngredient10: String?,
    @SerializedName("strMeasure10")
    private val strMeasure10: String?,
    @SerializedName("strIngredient11")
    private val strIngredient11: String?,
    @SerializedName("strMeasure11")
    private val strMeasure11: String?,
    @SerializedName("strIngredient12")
    private val strIngredient12: String?,
    @SerializedName("strMeasure12")
    private val strMeasure12: String?,
    @SerializedName("strIngredient13")
    private val strIngredient13: String?,
    @SerializedName("strMeasure13")
    private val strMeasure13: String?,
    @SerializedName("strIngredient14")
    private val strIngredient14: String?,
    @SerializedName("strMeasure14")
    private val strMeasure14: String?,
    @SerializedName("strIngredient15")
    private val strIngredient15: String?,
    @SerializedName("strMeasure15")
    private val strMeasure15: String?,
    @SerializedName("strIngredient16")
    private val strIngredient16: String?,
    @SerializedName("strMeasure16")
    private val strMeasure16: String?,
    @SerializedName("strIngredient17")
    private val strIngredient17: String?,
    @SerializedName("strMeasure17")
    private val strMeasure17: String?,
    @SerializedName("strIngredient18")
    private val strIngredient18: String?,
    @SerializedName("strMeasure18")
    private val strMeasure18: String?,
    @SerializedName("strIngredient19")
    private val strIngredient19: String?,
    @SerializedName("strMeasure19")
    private val strMeasure19: String?,
    @SerializedName("strIngredient20")
    private val strIngredient20: String?,
    @SerializedName("strMeasure20")
    private val strMeasure20: String?,
){
    fun <T> map(mapper: DishMapper<T>) = mapper.mapSuccess(
        id, dishName, tag, imageURL, collectIngredientsList()
    )

    fun <T> extendedMap(mapper: DishMapper<T>) = mapper.mapExtendedData(
        id, area, instructions, youtubeLink, sourceLink ?: ""
    )

    private fun collectIngredientsList() : List<Ingredient>{
        val ingredients = mutableListOf<Ingredient>()
        strIngredient1.appendNotEmptyToList(ingredients, strMeasure1)
        strIngredient2.appendNotEmptyToList(ingredients, strMeasure2)
        strIngredient3.appendNotEmptyToList(ingredients, strMeasure3)
        strIngredient4.appendNotEmptyToList(ingredients, strMeasure4)
        strIngredient5.appendNotEmptyToList(ingredients, strMeasure5)
        strIngredient6.appendNotEmptyToList(ingredients, strMeasure6)
        strIngredient7.appendNotEmptyToList(ingredients, strMeasure7)
        strIngredient8.appendNotEmptyToList(ingredients, strMeasure8)
        strIngredient9.appendNotEmptyToList(ingredients, strMeasure9)
        strIngredient10.appendNotEmptyToList(ingredients, strMeasure10)
        strIngredient11.appendNotEmptyToList(ingredients, strMeasure11)
        strIngredient12.appendNotEmptyToList(ingredients, strMeasure12)
        strIngredient13.appendNotEmptyToList(ingredients, strMeasure13)
        strIngredient14.appendNotEmptyToList(ingredients, strMeasure14)
        strIngredient15.appendNotEmptyToList(ingredients, strMeasure15)
        strIngredient16.appendNotEmptyToList(ingredients, strMeasure16)
        strIngredient17.appendNotEmptyToList(ingredients, strMeasure17)
        strIngredient18.appendNotEmptyToList(ingredients, strMeasure18)
        strIngredient19.appendNotEmptyToList(ingredients, strMeasure19)
        strIngredient20.appendNotEmptyToList(ingredients, strMeasure20)
        return ingredients
    }
}

private fun String?.appendNotEmptyToList(
    ingredients: MutableList<Ingredient>,
    measure: String?
) {
    if(this == null || measure == null)
        return
    if(isNotBlank() && measure.isNotBlank()){
        ingredients.add(Ingredient(this, measure))
    }
}
