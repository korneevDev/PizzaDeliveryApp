package github.mik0war.pizzadeliveryapp.feature.tags.data.cache

import github.mik0war.pizzadeliveryapp.core.DataMapper
import github.mik0war.pizzadeliveryapp.feature.tags.dataModel.TagDataModel
import github.mik0war.pizzadeliveryapp.recycler_list.data.CacheGetDataListDataSource
import javax.inject.Inject

class TagCacheGetDataListDataSource @Inject constructor(
    private val cacheDAO: TagCacheDAO,
    private val mapper: DataMapper<TagCacheEntity, TagDataModel>,
    private val reverseMapper: DataMapper<TagDataModel, TagCacheEntity>
) : CacheGetDataListDataSource<TagDataModel> {
    override suspend fun saveListData(list: List<TagDataModel>) {
        cacheDAO.clearTable()
        list.forEach { cacheDAO.saveDish(reverseMapper.map(it)) }
    }

    override suspend fun getListData(): List<TagDataModel> =
        cacheDAO.getCachedData().map { mapper.map(it) }

}