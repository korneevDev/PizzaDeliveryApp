package github.mik0war.pizzadeliveryapp.recycler_list.domain

interface GetDataListRepository<T>{
    suspend fun getObjectsList(): List<T>
}