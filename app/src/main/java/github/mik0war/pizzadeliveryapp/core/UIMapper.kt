package github.mik0war.pizzadeliveryapp.core

import android.view.View
import github.mik0war.pizzadeliveryapp.dish.domain.DishMapper

interface UIMapper : DishMapper<Unit> {
    fun getSuccessRoot(): View
    fun getErrorRoot(): View
    fun getExtendedRoot(): View
}