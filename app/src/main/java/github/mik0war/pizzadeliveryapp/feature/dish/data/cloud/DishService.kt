package github.mik0war.pizzadeliveryapp.feature.dish.data.cloud

import github.mik0war.pizzadeliveryapp.BuildConfig
import retrofit2.http.GET

interface DishService {

    @GET("search.php?s")
    suspend fun getListObject() : DishListServerModel

}