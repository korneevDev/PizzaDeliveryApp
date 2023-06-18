package github.mik0war.pizzadeliveryapp.feature.dish.data.cloud

import github.mik0war.pizzadeliveryapp.core.DataMapper
import github.mik0war.pizzadeliveryapp.feature.dish.dishDataModel.DishDataModel
import github.mik0war.pizzadeliveryapp.recycler_list.data.CloudGetDataListDataSource
import javax.inject.Inject

class DishCloudGetDataListDataSource @Inject constructor(
    private val dishService: DishService,
    private val mapper: DataMapper<DishServerModel, DishDataModel>
) : CloudGetDataListDataSource.Base<DishDataModel>() {

    override suspend fun getDataFromCloud(): List<DishDataModel> =
        dishService.getListObject().getList().map { mapper.map(it)}
}