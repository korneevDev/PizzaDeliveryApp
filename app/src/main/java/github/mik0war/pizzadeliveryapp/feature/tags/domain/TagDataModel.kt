package github.mik0war.pizzadeliveryapp.feature.tags.domain

class TagDataModel(
    private val id: Int,
    private val name: String,
    private val isMain: Boolean = false
) {
    fun <T> map(mapper: TagMapper<T>) = mapper.mapSuccess(id, name, isMain)
}
