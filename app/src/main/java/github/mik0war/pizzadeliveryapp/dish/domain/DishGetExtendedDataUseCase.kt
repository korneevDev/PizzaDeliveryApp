package github.mik0war.pizzadeliveryapp.dish.domain

import javax.inject.Inject

class DishGetExtendedDataUseCase @Inject constructor(
    private val repository: DishLoadExtendedDataRepository
) {

    suspend fun getExtendedData(id: String) = repository.getExtendedData(id)
}