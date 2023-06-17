package github.mik0war.pizzadeliveryapp.core

import github.mik0war.entity.CustomTextView
import github.mik0war.pizzadeliveryapp.core.mapper.MapperTo

interface Entity {
    fun<R: MapperTo<T>, T> map(mapper: R): T
}

interface UIEntity<T> : Entity {
    fun equalsId(other: T): Boolean
    fun getUrl(): String

    fun show(nameView: CustomTextView)
}