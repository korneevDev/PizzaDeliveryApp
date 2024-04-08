package github.mik0war.pizzadeliveryapp.feature.tags.domain

interface TagMapper<T> {
    fun mapSuccess(id: Int, name: String, isMain: Boolean): T
}