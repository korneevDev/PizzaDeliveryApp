package github.mik0war.pizzadeliveryapp

import android.app.Application
import github.mik0war.pizzadeliveryapp.di.DaggerDeliveryComponent
import github.mik0war.pizzadeliveryapp.di.DeliveryComponent

class PizzaDeliveryApp : Application() {
    val appComponent : DeliveryComponent by lazy {
        DaggerDeliveryComponent.factory().create(this)
    }
}