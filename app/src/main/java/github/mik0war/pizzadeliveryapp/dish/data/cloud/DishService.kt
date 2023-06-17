package github.mik0war.pizzadeliveryapp.dish.data.cloud

import github.mik0war.pizzadeliveryapp.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Headers

interface DishService {
    @Headers(
        "X-RapidAPI-Key: $API_KEY",
        "X-RapidAPI-Host: $API_HOST"
    )
    @GET("/productos")
    suspend fun getListObject() : CategoryListServerModel

    companion object{
        const val API_HOST = BuildConfig.xRapidApiHost
        const val API_KEY = BuildConfig.xRapidApiKey
    }
}