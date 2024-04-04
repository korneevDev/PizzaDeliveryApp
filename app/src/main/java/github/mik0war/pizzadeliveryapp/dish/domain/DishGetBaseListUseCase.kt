package github.mik0war.pizzadeliveryapp.dish.domain

import javax.inject.Inject


class DishGetBaseListUseCase @Inject constructor(
    private val repository: DishGetListRepository,
    private val exceptionHandler: DishExceptionHandler
) {
    suspend fun getDishList(): List<DishDataModel> =
        try {
            repository.getBaseDishList()
        } catch (e: Exception) {
            listOf(exceptionHandler.mapExceptionToModel(e))
        }

}
