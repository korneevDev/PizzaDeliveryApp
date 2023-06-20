package github.mik0war.pizzadeliveryapp.recycler_list.data

import github.mik0war.entity.NoCachedDataException

interface CacheGetDataListDataSource<T> : GetDataListDataSource<T> {
    suspend fun saveListData(list: List<T>)

    abstract class Abstract<T> : CacheGetDataListDataSource<T> {

        abstract suspend fun getDataFromCache(): List<T>
        override suspend fun getListData(): List<T> =
            getDataFromCache().ifEmpty { throw NoCachedDataException() }

    }
}