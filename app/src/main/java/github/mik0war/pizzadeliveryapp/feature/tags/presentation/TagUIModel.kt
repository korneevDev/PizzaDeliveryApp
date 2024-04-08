package github.mik0war.pizzadeliveryapp.feature.tags.presentation

import github.mik0war.pizzadeliveryapp.core.CustomTextView
import github.mik0war.pizzadeliveryapp.core.UIModel

sealed class TagUIModel(
    protected val id: Int,
    protected val name: String,
    protected val isMain: Boolean
) : UIModel {

    fun show(view: CustomTextView){
        view.set(name)
    }

    fun isFits(tag: String) = isMain || name == tag
    abstract fun click(): TagUIModel
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if(other == null) return false
        other as TagUIModel

        if (id != other.id) return false
        if (name != other.name) return false

        return true
    }



    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        return result
    }

    class Selected(
        id: Int,
        name: String,
        isMain: Boolean
    ) : TagUIModel(id, name, isMain) {
        override fun click(): TagUIModel = Common(id, name, isMain)
        override fun equalsId(other: UIModel): Boolean =
            other is Selected && other.id == id
    }

    class Common(
        id: Int,
        name: String,
        isMain: Boolean
    ) : TagUIModel(id, name, isMain) {
        override fun click() = Selected(id, name, isMain)
        override fun equalsId(other: UIModel) =
            other is Common && other.id == id
    }
}