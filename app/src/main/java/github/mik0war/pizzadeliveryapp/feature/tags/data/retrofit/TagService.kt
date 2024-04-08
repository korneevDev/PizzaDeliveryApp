package github.mik0war.pizzadeliveryapp.feature.tags.data.retrofit

import retrofit2.http.GET

interface TagService {
    @GET("categories.php")
    suspend fun getListObject() : TagsListServerModel

}