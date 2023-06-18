package github.mik0war.pizzadeliveryapp.dish.presentation

import android.view.View
import github.mik0war.pizzadeliveryapp.core.dishDataModel.DishUIModel
import github.mik0war.pizzadeliveryapp.recycler_list.presentation.TransferDataGetter

class CategoryTransferDataGetter :
    TransferDataGetter<DishUIModel, Int> {
    override fun getTransferData(uiModel: DishUIModel, view: View) =
        (uiModel as DishUIModel.Success).getCurrentPrice()
}