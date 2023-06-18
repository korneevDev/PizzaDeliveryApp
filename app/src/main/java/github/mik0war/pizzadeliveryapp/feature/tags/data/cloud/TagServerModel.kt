package github.mik0war.pizzadeliveryapp.feature.tags.data.cloud

import com.google.gson.annotations.SerializedName
import github.mik0war.pizzadeliveryapp.core.Entity
import github.mik0war.pizzadeliveryapp.core.mapper.MapperTo
import github.mik0war.pizzadeliveryapp.core.mapper.TagMapperTo

data class TagsListServerModel(
    @SerializedName("data")
    private val productList: List<TagServerModel>
) {
    fun getList() = productList
}

data class TagServerModel(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("nombre")
    private val name: String
) : Entity {

    override fun <R : MapperTo<T>, T> map(mapper: R): T =
        (mapper as TagMapperTo<T>).mapSuccess(id, name)
}