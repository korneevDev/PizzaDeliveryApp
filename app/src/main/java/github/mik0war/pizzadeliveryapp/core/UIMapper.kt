package github.mik0war.pizzadeliveryapp.core

import android.view.View
import github.mik0war.pizzadeliveryapp.dish.domain.DishMapper

interface UIMapper<T> : DishMapper<Unit> {
    fun getSuccessRoot(): View
    fun getDialogBinding(): T
    fun getErrorRoot(): View
    fun getExtendedRoot(): View
}