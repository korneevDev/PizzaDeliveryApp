package github.mik0war.pizzadeliveryapp.feature.tags.domain

interface TagRepository {

    suspend fun getTagList() : List<TagDataModel>
}