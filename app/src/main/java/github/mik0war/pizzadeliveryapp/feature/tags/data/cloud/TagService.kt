package github.mik0war.pizzadeliveryapp.feature.tags.data.cloud

import github.mik0war.pizzadeliveryapp.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Headers

interface TagService {
    @Headers(
        "X-RapidAPI-Key: $API_KEY",
        "X-RapidAPI-Host: $API_HOST"
    )
    @GET("/tags")
    suspend fun getListObject() : TagsListServerModel

    companion object{
        const val API_HOST = BuildConfig.xRapidApiHost
        const val API_KEY = BuildConfig.xRapidApiKey
    }
}