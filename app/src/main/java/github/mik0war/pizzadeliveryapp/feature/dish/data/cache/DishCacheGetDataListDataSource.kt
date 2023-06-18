package github.mik0war.pizzadeliveryapp.feature.dish.data.cache

import github.mik0war.pizzadeliveryapp.core.DataMapper
import github.mik0war.pizzadeliveryapp.feature.dish.dishDataModel.DishDataModel
import github.mik0war.pizzadeliveryapp.recycler_list.data.CacheGetDataListDataSource
import javax.inject.Inject

class DishCacheGetDataListDataSource @Inject constructor(
    private val cacheDAO: DishCacheDAO,
    private val mapper: DataMapper<DishCacheEntity, DishDataModel>,
    private val reverseMapper: DataMapper<DishDataModel, DishCacheEntity>
) : CacheGetDataListDataSource<DishDataModel> {
    override suspend fun saveListData(list: List<DishDataModel>) {
        cacheDAO.clearTable()
        list.forEach { cacheDAO.saveDish(reverseMapper.map(it)) }
    }

    override suspend fun getListData(): List<DishDataModel> =
        cacheDAO.getCachedData().map { mapper.map(it) }

}