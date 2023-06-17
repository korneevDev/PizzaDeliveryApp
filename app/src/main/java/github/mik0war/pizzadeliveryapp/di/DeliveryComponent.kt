package github.mik0war.pizzadeliveryapp.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import github.mik0war.pizzadeliveryapp.core.HomeFragment
import github.mik0war.pizzadeliveryapp.dish.di.DishDataBindsModule
import github.mik0war.pizzadeliveryapp.dish.di.DishDataProviderModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        CoreModule::class,
        DishDataBindsModule::class,
        DishDataProviderModule::class
    ]
)
interface DeliveryComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): DeliveryComponent
    }

    fun inject(fragment: HomeFragment)

}