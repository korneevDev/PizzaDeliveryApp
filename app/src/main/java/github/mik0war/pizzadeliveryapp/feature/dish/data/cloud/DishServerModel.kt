package github.mik0war.pizzadeliveryapp.feature.dish.data.cloud

import com.google.gson.annotations.SerializedName
import github.mik0war.pizzadeliveryapp.core.Entity
import github.mik0war.pizzadeliveryapp.core.mapper.DishMapperTo
import github.mik0war.pizzadeliveryapp.core.mapper.MapperTo

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
    private val name: String,
    @SerializedName("strInstructions")
    private val description: String,
    @SerializedName("strMealThumb")
    private val imageUrl: String?
) : Entity {

    override fun <R : MapperTo<T>, T> map(mapper: R): T =
        (mapper as DishMapperTo<T>).mapSuccess(
            id.toIntOrNull() ?: 0,
            name,
            description,
            1,
            imageUrl
                ?: "https://objectstorage.us-ashburn-1.oraclecloud.com/n/idkur78yryic/b/mamacocina/o/840_560.jpg"
        )
}