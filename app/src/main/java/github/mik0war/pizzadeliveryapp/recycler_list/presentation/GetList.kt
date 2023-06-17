package github.mik0war.recycler_list.presentation

import androidx.recyclerview.widget.DiffUtil

interface GetList<T> {
    fun getList(): List<T>
    fun getDiffUtilResult(): DiffUtil.DiffResult
}