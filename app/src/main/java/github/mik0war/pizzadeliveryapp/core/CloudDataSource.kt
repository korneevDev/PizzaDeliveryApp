package github.mik0war.pizzadeliveryapp.core

import android.util.Log
import java.net.UnknownHostException

interface CloudDataSource<T> {
    suspend fun getBaseListData(): List<T>
    abstract class Abstract<T> : CloudDataSource<T> {
        abstract suspend fun getDataFromCloud(): List<T>
        override suspend fun getBaseListData(): List<T> =
            try {
                getDataFromCloud()
            } catch (e: UnknownHostException) {
                throw NoConnectionException()
            } catch (e: Exception) {
                Log.e("kek", e.stackTraceToString())
                throw ServiceUnavailableException()
            }
    }
}


