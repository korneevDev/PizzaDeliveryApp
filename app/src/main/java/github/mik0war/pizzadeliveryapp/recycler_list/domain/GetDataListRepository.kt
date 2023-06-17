package github.mik0war.pizzadeliveryapp.recycler_list.domain

interface GetDataListRepository<T>{
    suspend fun getCategoryList(): List<T>
}