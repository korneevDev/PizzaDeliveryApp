package github.mik0war.pizzadeliveryapp.dish.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import github.mik0war.pizzadeliveryapp.core.DispatchersController
import github.mik0war.pizzadeliveryapp.dish.data.retrofit.DishService
import github.mik0war.pizzadeliveryapp.dish.data.room.DishCacheDAO
import github.mik0war.pizzadeliveryapp.dish.data.room.DishDataBase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object DishProvidesModule {
    @Provides
    fun provideRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .baseUrl("https://themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    fun provideDishRoom(@ApplicationContext context: Context): DishDataBase = Room.databaseBuilder(
        context,
        DishDataBase::class.java, "database-dish"
    ).build()

    @Provides
    fun provideDishDAO(db: DishDataBase): DishCacheDAO = db.provideCacheDishDao()

    @Provides
    fun provideDishService(retrofit: Retrofit): DishService =
        retrofit.create(DishService::class.java)

    @Provides
    fun provideDispatchers(): DispatchersController = DispatchersController.Base()
}