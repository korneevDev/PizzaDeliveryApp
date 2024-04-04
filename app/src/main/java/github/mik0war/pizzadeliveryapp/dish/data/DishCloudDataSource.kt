package github.mik0war.pizzadeliveryapp.dish.data

import github.mik0war.pizzadeliveryapp.dish.data.retrofit.DishService
import github.mik0war.pizzadeliveryapp.dish.domain.DishMapper
import github.mik0war.pizzadeliveryapp.core.CloudDataSource
import github.mik0war.pizzadeliveryapp.dish.domain.DishDataModel
import javax.inject.Inject

class DishCloudDataSource @Inject constructor(
    private val dishService: DishService,
    private val mapper: DishMapper<DishDataModel>
) : CloudDataSource.Abstract<Pair<DishDataModel, DishDataModel>>() {

    override suspend fun getDataFromCloud(): List<FullDishData> =
        dishService.getListObject().getList().map {
            Pair(
                it.map(mapper),
                it.extendedMap(mapper)
            )
        }
}