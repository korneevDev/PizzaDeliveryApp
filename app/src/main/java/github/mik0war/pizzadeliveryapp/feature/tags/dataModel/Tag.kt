package github.mik0war.pizzadeliveryapp.feature.tags.dataModel

import github.mik0war.pizzadeliveryapp.core.Entity
import github.mik0war.pizzadeliveryapp.core.mapper.MapperTo
import github.mik0war.pizzadeliveryapp.core.mapper.TagMapperTo

sealed class Tag(
    private val id: Int,
    private val name: String,
): Entity {
    override fun <R : MapperTo<T>, T> map(mapper: R): T =
        (mapper as TagMapperTo<T>).mapSuccess(id, name)

    fun getTagId() = id
    fun getTagName() = name

    class Common(id: Int, name: String): Tag(id, name)
    open class Selected(id: Int, name: String): Tag(id, name)
    class NewSelected(id: Int, name: String): Selected(id, name)

    class Error(name: String) : Tag(0, name){
        override fun <R : MapperTo<T>, T> map(mapper: R): T =
            (mapper as TagMapperTo<T>).mapError(getTagName())
    }
}