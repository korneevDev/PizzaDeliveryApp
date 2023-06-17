package github.mik0war.pizzadeliveryapp.dish.di

import dagger.Module
import dagger.Provides
import github.mik0war.pizzadeliveryapp.core.dishDataModel.DishDataModel
import github.mik0war.pizzadeliveryapp.core.dishDataModel.DishModel
import github.mik0war.pizzadeliveryapp.core.dishDataModel.DishUIModel
import github.mik0war.pizzadeliveryapp.core.mapper.DishMapperTo
import github.mik0war.pizzadeliveryapp.core.mapper.MapperTo
import github.mik0war.pizzadeliveryapp.dish.data.cache.DishCacheEntity
import github.mik0war.pizzadeliveryapp.dish.data.cache.DishDataBase
import github.mik0war.pizzadeliveryapp.dish.data.cloud.DishService
import retrofit2.Retrofit

@Module
class DishDataProviderModule {
    @Provides
    fun provideCategoryService(retrofit: Retrofit): DishService =
        retrofit.create(DishService::class.java)

    @Provides
    fun provideDAO(db: DishDataBase) = db.provideCacheDishDao()

    @Provides
    fun provideMapperToDishUIModel(): MapperTo<DishUIModel> =
        object : DishMapperTo<DishUIModel> {
            override fun mapSuccess(
                id: Int,
                name: String,
                description: String,
                price: Int,
                imageUrl: String
            ) = DishUIModel.Success(id, name, description, price, imageUrl)

            override fun mapError(errorName: String) = DishUIModel.Error(errorName)
        }

    @Provides
    fun provideMapperToDishModel(): MapperTo<DishModel> =
        object : DishMapperTo<DishModel>{
            override fun mapSuccess(
                id: Int,
                name: String,
                description: String,
                price: Int,
                imageUrl: String
            ): DishModel = DishModel.Success(id, name, description, price, imageUrl)

            override fun mapError(errorName: String): DishModel = DishModel.Error(errorName)
        }

    @Provides
    fun provideMapperToDishDataModel(): MapperTo<DishDataModel> =
        object : DishMapperTo<DishDataModel>{
            override fun mapSuccess(
                id: Int,
                name: String,
                description: String,
                price: Int,
                imageUrl: String
            ): DishDataModel = DishDataModel(id, name, description, price, imageUrl)

            override fun mapError(errorName: String): DishDataModel = throw IllegalStateException()
        }

    @Provides
    fun provideMapperToCacheModel(): MapperTo<DishCacheEntity> =
        object : DishMapperTo<DishCacheEntity>{
            override fun mapSuccess(
                id: Int,
                name: String,
                description: String,
                price: Int,
                imageUrl: String
            ) = DishCacheEntity(id, name, description, price, imageUrl)

            override fun mapError(errorName: String): DishCacheEntity = throw IllegalStateException()
        }
}