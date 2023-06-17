package github.mik0war.pizzadeliveryapp.recycler_list.data

interface GetDataListDataSource<T>{
    suspend fun getListData(): List<T>
}