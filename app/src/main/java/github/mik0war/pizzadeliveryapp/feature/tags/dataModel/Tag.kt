package github.mik0war.pizzadeliveryapp.feature.tags.dataModel

sealed class Tag(
    private val id: Int,
    private val name: String,
) {

    fun getTagId() = id
    fun getTagName() = name

    class Common(id: Int, name: String): Tag(id, name)
    open class Selected(id: Int, name: String): Tag(id, name)

    class Error(name: String) : Tag(0, name)
}