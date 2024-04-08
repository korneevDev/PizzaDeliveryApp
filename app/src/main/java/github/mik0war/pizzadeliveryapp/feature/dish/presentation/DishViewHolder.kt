package github.mik0war.pizzadeliveryapp.feature.dish.presentation

import android.view.View
import androidx.recyclerview.widget.RecyclerView

sealed class DishViewHolder(
    private val uiMapper: DishUIMapper,
    view: View) : RecyclerView.ViewHolder(view){
    fun bind(uiModel: DishUIModel){
        uiModel.map(uiMapper)
    }

    class Success(
        uiMapper: DishUIMapper
    ) : DishViewHolder(uiMapper, uiMapper.getSuccessRoot())

    class Error(
        uiMapper: DishUIMapper
    ) : DishViewHolder(uiMapper, uiMapper.getErrorRoot())
}