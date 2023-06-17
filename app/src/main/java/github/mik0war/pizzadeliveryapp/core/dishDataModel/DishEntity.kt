package github.mik0war.pizzadeliveryapp.core.dishDataModel

import com.google.gson.annotations.SerializedName
import github.mik0war.pizzadeliveryapp.core.Entity
import github.mik0war.pizzadeliveryapp.core.mapper.DishMapperTo
import github.mik0war.pizzadeliveryapp.core.mapper.MapperTo

sealed interface DishEntity : Entity {
    abstract class Success(
        protected val id: Int,
        protected val name: String,
        protected val description: String,
        protected val price: Int,
        protected val imageUrl: String
    ) : DishEntity {

        override fun <R : MapperTo<T>, T> map(mapper: R): T =
            (mapper as DishMapperTo<T>).mapSuccess(id, name, description, price, imageUrl)

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Success) return false

            if (id != other.id) return false
            if (name != other.name) return false
            if (imageUrl != other.imageUrl) return false

            return true
        }

        override fun hashCode(): Int {
            var result = id
            result = 31 * result + name.hashCode()
            result = 31 * result + imageUrl.hashCode()
            return result
        }
    }

    abstract class Error(
        protected val name: String,
    ) : DishEntity {

        override fun <R : MapperTo<T>, T> map(mapper: R): T =
            (mapper as DishMapperTo<T>).mapError(name)

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Error || name != other.name) return false

            return true
        }

        override fun hashCode(): Int {
            return name.hashCode()
        }
    }
}

