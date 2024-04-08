package github.mik0war.pizzadeliveryapp.feature.tags.data.retrofit

import com.google.gson.annotations.SerializedName
import github.mik0war.pizzadeliveryapp.feature.tags.domain.TagMapper

data class TagsListServerModel(
    @SerializedName("categories")
    private val productList: List<TagServerModel>
) {

    fun getTags() = productList
}

data class TagServerModel(
    @SerializedName("idCategory")
    private val id: Int,
    @SerializedName("strCategory")
    private val name: String
)  {

    fun <T> map(tagsMapper: TagMapper<T>) = tagsMapper.mapSuccess(id, name, false)
}