package github.mik0war.pizzadeliveryapp.dish.data.retrofit

import retrofit2.http.GET

interface DishService {

    @GET("search.php?s")
    suspend fun getListObject() : DishListServerModel

}