package github.mik0war.pizzadeliveryapp.core

import androidx.recyclerview.widget.DiffUtil

interface GetList<T> {
    fun getList(): List<T>
    fun getDiffUtilResult(): DiffUtil.DiffResult
}