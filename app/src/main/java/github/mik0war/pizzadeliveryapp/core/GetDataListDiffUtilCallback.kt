package github.mik0war.pizzadeliveryapp.core

import androidx.recyclerview.widget.DiffUtil

class GetDataListDiffUtilCallback<T: UIEntity>(
    private val oldList: List<T>,
    private val newList: List<T>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int)
        = oldList[oldItemPosition].equalsId(newList[newItemPosition])

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int)
        = oldList[oldItemPosition] == newList[newItemPosition]
}