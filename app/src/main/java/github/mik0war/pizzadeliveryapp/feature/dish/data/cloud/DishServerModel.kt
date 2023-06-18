package github.mik0war.pizzadeliveryapp.feature.dish.data.cloud

import com.google.gson.annotations.SerializedName
import github.mik0war.pizzadeliveryapp.core.Entity
import github.mik0war.pizzadeliveryapp.core.mapper.DishMapperTo
import github.mik0war.pizzadeliveryapp.core.mapper.MapperTo

data class DishListServerModel(
    @SerializedName("productos")
    private val productList: List<DishServerModel>
) {
    fun getList() = productList
}

data class DishServerModel(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("nombre")
    private val name: String,
    @SerializedName("descripcion")
    private val description: String,
    @SerializedName("precio")
    private val price: Int,
    @SerializedName("linkImagen")
    private val imageUrl: String?
) : Entity {

    override fun <R : MapperTo<T>, T> map(mapper: R): T =
        (mapper as DishMapperTo<T>).mapSuccess(
            id,
            name,
            description,
            price,
            imageUrl
                ?: "https://objectstorage.us-ashburn-1.oraclecloud.com/n/idkur78yryic/b/mamacocina/o/840_560.jpg"
        )
}