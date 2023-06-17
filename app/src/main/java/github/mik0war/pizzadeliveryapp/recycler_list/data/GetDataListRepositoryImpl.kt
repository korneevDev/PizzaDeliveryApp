package github.mik0war.pizzadeliveryapp.recycler_list.data

import android.util.Log
import github.mik0war.pizzadeliveryapp.di.IODispatcher
import github.mik0war.pizzadeliveryapp.recycler_list.domain.GetDataListRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetDataListRepositoryImpl<T> @Inject constructor(
    private val cloudDataSource: GetDataListDataSource<T>,
    private val cacheDataSource: CacheGetDataListDataSource<T>,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) : GetDataListRepository<T> {

    override suspend fun getCategoryList(): List<T> =
        withContext(dispatcher) {
            try {
                return@withContext cloudDataSource.getListData()
         //           .also { cacheDataSource.saveListData(it) }
            } catch (cloudException: Exception) {
                try {
                    return@withContext cacheDataSource.getListData()
                } catch (cacheException: java.lang.Exception){
                    throw cloudException
                }
            }
        }
}