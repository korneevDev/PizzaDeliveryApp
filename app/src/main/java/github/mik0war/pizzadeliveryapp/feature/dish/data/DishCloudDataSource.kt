package github.mik0war.pizzadeliveryapp.feature.dish.data

import github.mik0war.pizzadeliveryapp.core.NoConnectionException
import github.mik0war.pizzadeliveryapp.core.ServiceUnavailableException
import github.mik0war.pizzadeliveryapp.feature.dish.data.retrofit.DishService
import github.mik0war.pizzadeliveryapp.feature.dish.domain.DishDataModel
import github.mik0war.pizzadeliveryapp.feature.dish.domain.DishMapper
import java.net.UnknownHostException
import javax.inject.Inject

class DishCloudDataSource @Inject constructor(
    private val dishService: DishService,
    private val mapper: DishMapper<DishDataModel>
) {

    suspend fun getBaseListData(): List<FullDishData> =
        try {
            dishService.getListObject().getList().map {
                Pair(
                    it.map(mapper),
                    it.extendedMap(mapper)
                )
            }
        } catch (e: UnknownHostException) {
            throw NoConnectionException()
        } catch (e: Exception) {
            throw ServiceUnavailableException()
        }
}