package github.mik0war.pizzadeliveryapp.dish.presentation

import android.view.View
import github.mik0war.pizzadeliveryapp.core.dishDataModel.DishUIModel
import github.mik0war.recycler_list.presentation.TransferDataGetter

class CategoryTransferDataGetter :
    TransferDataGetter<DishUIModel, String> {
    override fun getTransferData(uiModel: DishUIModel, view: View) = ""
}