package github.mik0war.pizzadeliveryapp.feature.tags.dataModel

import github.mik0war.pizzadeliveryapp.core.Entity
import github.mik0war.pizzadeliveryapp.core.mapper.MapperTo
import github.mik0war.pizzadeliveryapp.core.mapper.TagMapperTo


class TagDataModel(
    private val id: Int,
    private val name: String
) : Entity {
    override fun <R : MapperTo<T>, T> map(mapper: R): T =
        (mapper as TagMapperTo<T>).mapSuccess(id, name)

}