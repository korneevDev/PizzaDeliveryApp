package github.mik0war.pizzadeliveryapp.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import github.mik0war.pizzadeliveryapp.presentation.HomeFragment
import github.mik0war.pizzadeliveryapp.feature.dish.di.DishDataBindsModule
import github.mik0war.pizzadeliveryapp.feature.dish.di.DishDataProviderModule
import github.mik0war.pizzadeliveryapp.feature.tags.di.TagDataBindsModule
import github.mik0war.pizzadeliveryapp.feature.tags.di.TagDataProviderModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        CoreModule::class,
        DishDataBindsModule::class,
        DishDataProviderModule::class,
        TagDataProviderModule::class,
        TagDataBindsModule::class
    ]
)
interface DeliveryComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): DeliveryComponent
    }

    fun inject(fragment: HomeFragment)

}