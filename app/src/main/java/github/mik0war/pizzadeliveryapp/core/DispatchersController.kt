package github.mik0war.pizzadeliveryapp.core

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

interface DispatchersController {
    fun io(): CoroutineDispatcher
    fun main(): CoroutineDispatcher

    class Base @Inject constructor() : DispatchersController {
        override fun io() = Dispatchers.IO

        override fun main() = Dispatchers.Main
    }
}