package github.mik0war.pizzadeliveryapp.feature.tags.data.cloud

import retrofit2.http.GET

interface TagService {
    @GET("https://themealdb.com/api/json/v1/1/categories.php")
    suspend fun getListObject() : TagsListServerModel

}