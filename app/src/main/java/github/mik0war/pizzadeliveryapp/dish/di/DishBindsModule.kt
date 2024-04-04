package github.mik0war.pizzadeliveryapp.dish.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import github.mik0war.pizzadeliveryapp.core.ImageLoader
import github.mik0war.pizzadeliveryapp.core.StringResourceProvider
import github.mik0war.pizzadeliveryapp.dish.data.DishGetBaseListRepositoryImpl
import github.mik0war.pizzadeliveryapp.dish.domain.Communication
import github.mik0war.pizzadeliveryapp.dish.domain.DishGetListRepository
import github.mik0war.pizzadeliveryapp.dish.presentation.DishUIModel

@Module
@InstallIn(SingletonComponent::class)
abstract class DishBindsModule {

    @Binds
    abstract fun bindImageLoader(loader: ImageLoader.Base): ImageLoader

    @Binds
    abstract fun bindGetDishDataListRepository(
        repository: DishGetBaseListRepositoryImpl
    ): DishGetListRepository



    @Binds
    abstract fun bindCommunication(
        communication: Communication.Base<DishUIModel>
    ): Communication<DishUIModel>

    @Binds
    abstract fun bindStringResourceProvider(
        provider: StringResourceProvider.Base
    ) : StringResourceProvider
}