package github.mik0war.pizzadeliveryapp.dish.presentation

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import github.mik0war.pizzadeliveryapp.core.UIMapper

sealed class DishViewHolder(
    private val uiMapper: UIMapper,
    view: View) : RecyclerView.ViewHolder(view){
    fun bind(uiModel: DishUIModel){
        uiModel.map(uiMapper)
    }

    class Success(
        uiMapper: UIMapper
    ) : DishViewHolder(uiMapper, uiMapper.getSuccessRoot())

    class Error(
        uiMapper: UIMapper
    ) : DishViewHolder(uiMapper, uiMapper.getErrorRoot())
}