package github.mik0war.pizzadeliveryapp.feature.tags.data

import github.mik0war.pizzadeliveryapp.feature.tags.data.room.TagDAO
import github.mik0war.pizzadeliveryapp.feature.tags.domain.TagDataModel
import github.mik0war.pizzadeliveryapp.feature.tags.domain.TagMapper
import javax.inject.Inject

class TagCacheDataSource @Inject constructor(
    private val dao: TagDAO,
    private val mapper: TagMapper<TagDataModel>
) {
    suspend fun getCachedList(): List<TagDataModel> =
        dao.getTagsList().map { it.map(mapper) }
}