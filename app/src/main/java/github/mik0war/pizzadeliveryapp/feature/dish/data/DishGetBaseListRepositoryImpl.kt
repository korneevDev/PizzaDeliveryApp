package github.mik0war.pizzadeliveryapp.feature.dish.data

import github.mik0war.pizzadeliveryapp.core.DispatchersController
import github.mik0war.pizzadeliveryapp.core.NoCachedDataException
import github.mik0war.pizzadeliveryapp.feature.dish.domain.DishDataModel
import github.mik0war.pizzadeliveryapp.feature.dish.domain.DishExceptionHandler
import github.mik0war.pizzadeliveryapp.feature.dish.domain.DishGetListRepository
import github.mik0war.pizzadeliveryapp.feature.dish.domain.DishLoadExtendedDataRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DishGetBaseListRepositoryImpl @Inject constructor(
    private val cloudDataSource: DishCloudDataSource,
    private val cacheDataSource: DishCacheDataSource,
    private val exceptionHandler: DishExceptionHandler,
    private val dispatchersController: DispatchersController
) : DishGetListRepository {
    override suspend fun getBaseDishList(): List<DishDataModel> =
        withContext(dispatchersController.io()) {
            try {
                return@withContext cloudDataSource.getBaseListData()
                    .also { pairs ->
                        cacheDataSource.saveListData(pairs)
                    }
                    .map {
                        it.first
                    }
            } catch (cloudException: Exception) {
                try {
                    return@withContext listOf(
                        exceptionHandler.mapExceptionToModel(cloudException)
                    ) + cacheDataSource.getBaseListData()
                } catch (cacheException: NoCachedDataException) {
                    throw cloudException
                }
            }
        }
}

class DishLoadExtendedDataRepositoryImpl @Inject constructor(
    private val cacheDataSource: DishCacheDataSource,
    private val dispatchersController: DispatchersController
) : DishLoadExtendedDataRepository {
    override suspend fun getExtendedData(id: String): DishDataModel =
        withContext(dispatchersController.io()) {
            return@withContext cacheDataSource.getExtendedData(id)
        }
}