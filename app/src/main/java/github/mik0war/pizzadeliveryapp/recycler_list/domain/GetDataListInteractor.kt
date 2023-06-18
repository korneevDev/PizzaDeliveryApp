package github.mik0war.pizzadeliveryapp.recycler_list.domain

import github.mik0war.pizzadeliveryapp.core.DataMapper
import github.mik0war.pizzadeliveryapp.core.Entity
import github.mik0war.entity.ExceptionHandler
import javax.inject.Inject

interface GetDataListInteractor<R> {
    suspend fun getDataList(tags: List<String> = emptyList()): List<R>

    class Base<S : Entity, R : Entity> @Inject constructor(
        private val repository: GetDataListRepository<S>,
        private val mapper: DataMapper<S, R>,
        private val exceptionHandler: ExceptionHandler<R>
    ) : GetDataListInteractor<R> {
        override suspend fun getDataList(tags: List<String>): List<R> =
            try {
                repository.getObjectsList()
                    .map { mapper.map(it) }
            } catch (e: Exception) {
                listOf(exceptionHandler.mapExceptionToModel(e))
            }
    }
}