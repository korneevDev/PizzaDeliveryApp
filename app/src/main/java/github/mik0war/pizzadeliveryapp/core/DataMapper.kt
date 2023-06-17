package github.mik0war.pizzadeliveryapp.core

import github.mik0war.pizzadeliveryapp.core.mapper.MapperTo
import javax.inject.Inject


interface DataMapper<S: Entity, R: Entity> {
    fun map(dataObject: S): R

    class Base<S: Entity, R: Entity> @Inject constructor(
        private val mapperTo: MapperTo<R>
    ) : DataMapper<S, R> {
        override fun map(dataObject: S): R =
            dataObject.map(mapperTo)
    }
}