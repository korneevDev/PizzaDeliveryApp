package github.mik0war.pizzadeliveryapp.recycler_list.data


import github.mik0war.entity.NoConnectionException
import github.mik0war.entity.ServiceUnavailableException
import java.net.UnknownHostException

interface CloudGetDataListDataSource<T>: GetDataListDataSource<T> {
    abstract class Base<T> : CloudGetDataListDataSource<T> {
        abstract suspend fun getDataFromCloud(): List<T>
        override suspend fun getListData(): List<T> =
            try {
                getDataFromCloud()
            } catch (e: Exception) {
                throw if (e is UnknownHostException)
                    NoConnectionException()
                else ServiceUnavailableException()
            }
    }
}


