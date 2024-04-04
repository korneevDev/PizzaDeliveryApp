package github.mik0war.pizzadeliveryapp.dish.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import github.mik0war.pizzadeliveryapp.core.GetList
import github.mik0war.pizzadeliveryapp.dish.presentation.DishUIModel
import github.mik0war.pizzadeliveryapp.dish.presentation.DishViewModel

@Module
@InstallIn(FragmentComponent::class)
abstract class DishVMBinds {
    @Binds
    abstract fun bindGetListViewModel(vm: DishViewModel): GetList<DishUIModel>

    @Binds
    abstract fun bindViewModel(vm: DishViewModel): DishViewModel

}