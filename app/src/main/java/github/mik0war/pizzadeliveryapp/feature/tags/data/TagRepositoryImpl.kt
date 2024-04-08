package github.mik0war.pizzadeliveryapp.feature.tags.data

import github.mik0war.pizzadeliveryapp.feature.tags.domain.TagDataModel
import github.mik0war.pizzadeliveryapp.feature.tags.domain.TagRepository
import javax.inject.Inject

class TagRepositoryImpl @Inject constructor(
    private val cacheDataSource: TagCacheDataSource,
    private val cloudDataSource: TagCloudDataSource
) : TagRepository {
    override suspend fun getTagList(): List<TagDataModel> =
        try {
            cloudDataSource.getDataFromCloud()
        } catch (_: Exception) {
            cacheDataSource.getCachedList()
        }

}