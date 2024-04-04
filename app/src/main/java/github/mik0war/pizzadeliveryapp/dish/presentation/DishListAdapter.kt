package github.mik0war.pizzadeliveryapp.dish.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import github.mik0war.pizzadeliveryapp.core.GetList
import github.mik0war.pizzadeliveryapp.core.ImageLoader
import github.mik0war.pizzadeliveryapp.databinding.DishEmptyObjectBinding
import github.mik0war.pizzadeliveryapp.databinding.DishObjectBinding
import github.mik0war.pizzadeliveryapp.databinding.ExtendedDishObjectBinding
import javax.inject.Inject

class DishListAdapter @Inject constructor(
    private val itemList: GetList<DishUIModel>,
    private val imageLoader: ImageLoader,
    private val dialogClickLambda: (uiMapper : DishUIMapper) -> Unit
) : RecyclerView.Adapter<DishViewHolder>(){

    fun update(){
        itemList.getDiffUtilResult().dispatchUpdatesTo(this)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val uiMapper = DishUIMapper(
            DishObjectBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            DishEmptyObjectBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            ExtendedDishObjectBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            imageLoader,
            dialogClickLambda
        )
        val holder =  when (viewType) {
            1 -> DishViewHolder.Success(uiMapper)
            2 -> DishViewHolder.Error(uiMapper)
            else -> {
                throw IllegalStateException()
            }

        }
        return holder
    }

    override fun getItemCount() = itemList.getList().size

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        holder.bind(itemList.getList()[position])
    }

    override fun getItemViewType(position: Int): Int =
        when(itemList.getList()[position]){
            is DishUIModel.Base -> 1
            is DishUIModel.Error -> 2
            is DishUIModel.Extended -> 3
        }
}