package github.mik0war.pizzadeliveryapp.dish.presentation

import androidx.constraintlayout.widget.ConstraintLayout
import github.mik0war.pizzadeliveryapp.R
import github.mik0war.pizzadeliveryapp.core.dishDataModel.DishUIModel
import github.mik0war.recycler_list.presentation.GetList
import github.mik0war.recycler_list.presentation.ImageLoader
import github.mik0war.recycler_list.presentation.RecyclerViewAdapter

class CategoryRecyclerViewAdapter(
    internetDataLiveData: GetList<DishUIModel>,
    imageLoader: ImageLoader,
    transferDataGetter: CategoryTransferDataGetter
) : RecyclerViewAdapter<DishUIModel, String, ConstraintLayout>(
    internetDataLiveData,
    imageLoader,
    transferDataGetter
) {
    override fun errorClass(model: DishUIModel) = model is DishUIModel.Error
    override fun getSuccessLayout() = R.layout.dish_object
    override fun getClickableId() = R.id.objectLayout
}