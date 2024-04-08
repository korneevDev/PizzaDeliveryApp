package github.mik0war.pizzadeliveryapp.feature.dish.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import github.mik0war.pizzadeliveryapp.feature.dish.data.room.DishCacheEntity
import github.mik0war.pizzadeliveryapp.feature.dish.data.room.ExtendedDishCacheEntity
import github.mik0war.pizzadeliveryapp.feature.dish.data.room.IngredientCacheModel
import github.mik0war.pizzadeliveryapp.feature.dish.domain.DishDataModel
import github.mik0war.pizzadeliveryapp.feature.dish.domain.DishMapper
import github.mik0war.pizzadeliveryapp.feature.dish.domain.Ingredient
import github.mik0war.pizzadeliveryapp.feature.dish.domain.IngredientMapper
import github.mik0war.pizzadeliveryapp.feature.dish.presentation.DishUIModel


@Module
@InstallIn(SingletonComponent::class)
object DishMapperProvidesModule {

    @Provides
    fun provideDishMapperToDataModel(): DishMapper<DishDataModel> =
        object : DishMapper<DishDataModel> {
            override fun mapSuccess(
                id: String,
                dishName: String,
                tag: String,
                imageURL: String,
                ingredients: List<Ingredient>
            ) = DishDataModel.Base(id, dishName, tag, imageURL, ingredients)

            override fun mapError(errorName: String, errorImageId: Int) =
                DishDataModel.Error(errorName, errorImageId)

            override fun mapExtendedData(
                id: String,
                area: String,
                instructions: String,
                youtubeLink: String,
                sourceLink: String
            ) = DishDataModel.Extended(id, area, instructions, youtubeLink, sourceLink)
        }

    @Provides
    fun provideDishMapperToUIModel(): DishMapper<DishUIModel> =
        object : DishMapper<DishUIModel> {
            override fun mapSuccess(
                id: String,
                dishName: String,
                tag: String,
                imageURL: String,
                ingredients: List<Ingredient>
            ) = DishUIModel.Base(id, dishName, tag, imageURL, ingredients)

            override fun mapError(errorName: String, errorImageId: Int) =
                DishUIModel.Error(errorName, errorImageId)

            override fun mapExtendedData(
                id: String,
                area: String,
                instructions: String,
                youtubeLink: String,
                sourceLink: String
            ) = DishUIModel.Extended(id, area, instructions, youtubeLink, sourceLink)
        }

    @Provides
    fun provideDishMapperToCacheModel(): DishMapper<DishCacheEntity> =
        object : DishMapper<DishCacheEntity> {
            override fun mapSuccess(
                id: String,
                dishName: String,
                tag: String,
                imageURL: String,
                ingredients: List<Ingredient>
            ): DishCacheEntity =
                DishCacheEntity(id, dishName, tag, imageURL, null)


            override fun mapError(errorName: String, errorImageId: Int): DishCacheEntity {
                throw IllegalStateException()
            }

            override fun mapExtendedData(
                id: String,
                area: String,
                instructions: String,
                youtubeLink: String,
                sourceLink: String
            ): DishCacheEntity = throw IllegalStateException()
        }

    @Provides
    fun provideDishMapperToExtendedCacheModel(): DishMapper<ExtendedDishCacheEntity> =
        object : DishMapper<ExtendedDishCacheEntity> {
            override fun mapSuccess(
                id: String,
                dishName: String,
                tag: String,
                imageURL: String,
                ingredients: List<Ingredient>
            ): ExtendedDishCacheEntity {
                throw IllegalStateException()
            }

            override fun mapError(errorName: String, errorImageId: Int): ExtendedDishCacheEntity {
                throw IllegalStateException()
            }

            override fun mapExtendedData(
                id: String,
                area: String,
                instructions: String,
                youtubeLink: String,
                sourceLink: String
            ) = ExtendedDishCacheEntity(area, instructions, youtubeLink, sourceLink)

        }


    @Provides
    fun provideIngredientToIngredientCacheModel(): IngredientMapper<IngredientCacheModel> =
        object : IngredientMapper<IngredientCacheModel> {
            override fun map(dishId: String, name: String, measure: String) =
                IngredientCacheModel(dishId, name, measure)
        }

    @Provides
    fun provideIngredientCacheToIngredientModel(): IngredientMapper<Ingredient> =
        object : IngredientMapper<Ingredient> {
            override fun map(dishId: String, name: String, measure: String) =
                Ingredient(name, measure)
        }

}