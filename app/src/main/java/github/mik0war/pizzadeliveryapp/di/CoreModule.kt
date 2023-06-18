package github.mik0war.pizzadeliveryapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import github.mik0war.pizzadeliveryapp.feature.dish.data.cache.DishDataBase
import github.mik0war.pizzadeliveryapp.feature.tags.data.cache.TagDataBase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class CoreModule {
    @IODispatcher
    @Provides
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @MainDispatcher
    @Provides
    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .baseUrl("https://pizzaallapala.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideDishRoom(context: Context): DishDataBase = Room.databaseBuilder(
        context,
        DishDataBase::class.java, "database-dish"
    ).build()

    @Singleton
    @Provides
    fun provideTagRoom(context: Context): TagDataBase = Room.databaseBuilder(
        context,
        TagDataBase::class.java, "database-tag"
    ).build()
}