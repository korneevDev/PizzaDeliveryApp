package github.mik0war.pizzadeliveryapp.feature.dish.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import github.mik0war.pizzadeliveryapp.core.Communication
import github.mik0war.pizzadeliveryapp.core.ImageLoader
import github.mik0war.pizzadeliveryapp.core.StringResourceProvider
import github.mik0war.pizzadeliveryapp.feature.dish.data.DishGetBaseListRepositoryImpl
import github.mik0war.pizzadeliveryapp.feature.dish.data.DishLoadExtendedDataRepositoryImpl
import github.mik0war.pizzadeliveryapp.feature.dish.domain.DishGetListRepository
import github.mik0war.pizzadeliveryapp.feature.dish.domain.DishLoadExtendedDataRepository
import github.mik0war.pizzadeliveryapp.feature.dish.presentation.DishDialogConfigurator
import github.mik0war.pizzadeliveryapp.feature.dish.presentation.DishUIModel

@Module
@InstallIn(SingletonComponent::class)
abstract class DishBindsModule {

    @Binds
    abstract fun bindImageLoader(loader: ImageLoader.Base): ImageLoader

    @Binds
    abstract fun bindDialogConf(conf: DishDialogConfigurator.Base) : DishDialogConfigurator
    @Binds
    abstract fun bindGetDishDataListRepository(
        repository: DishGetBaseListRepositoryImpl
    ): DishGetListRepository

    @Binds
    abstract fun bindGetDishExtendedDataListRepository(
        repository: DishLoadExtendedDataRepositoryImpl
    ): DishLoadExtendedDataRepository

    @Binds
    abstract fun bindCommunication(
        communication: Communication.Base<DishUIModel>
    ): Communication<DishUIModel>

    @Binds
    abstract fun bindStringResourceProvider(
        provider: StringResourceProvider.Base
    ) : StringResourceProvider
}