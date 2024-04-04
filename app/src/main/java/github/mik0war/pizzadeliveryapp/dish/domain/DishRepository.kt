package github.mik0war.pizzadeliveryapp.dish.domain

interface DishGetListRepository {
    suspend fun getBaseDishList(): List<DishDataModel>
}

interface DishLoadExtendedDataRepository{
    suspend fun getExtendedData(id: String): DishDataModel
}