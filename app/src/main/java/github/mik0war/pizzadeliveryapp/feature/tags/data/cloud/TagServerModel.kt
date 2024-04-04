package github.mik0war.pizzadeliveryapp.feature.tags.data.cloud

import com.google.gson.annotations.SerializedName
import github.mik0war.pizzadeliveryapp.core.mapper.TagMapper

data class TagsListServerModel(
    @SerializedName("categories")
    private val productList: List<TagServerModel>
) {
}

data class TagServerModel(
    @SerializedName("idCategory")
    private val id: Int,
    @SerializedName("strCategory")
    private val name: String
)  {


    fun <T> map(tagsMapper: TagMapper<T>) = tagsMapper.mapSuccess(id, name)
}