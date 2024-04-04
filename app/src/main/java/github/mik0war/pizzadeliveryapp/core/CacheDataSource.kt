package github.mik0war.pizzadeliveryapp.core

import github.mik0war.pizzadeliveryapp.dish.domain.DishDataModel

interface CacheDataSource<T> : CloudDataSource<T> {
    suspend fun saveListData(
        lists: List<Pair<DishDataModel, DishDataModel>>
    )

    suspend fun getExtendedData(id: String): T

    abstract class Abstract<T> : CacheDataSource<T> {

        abstract suspend fun getBaseData(): List<T>
        override suspend fun getBaseListData(): List<T> =
            getBaseData().ifEmpty { throw NoCachedDataException() }

    }
}