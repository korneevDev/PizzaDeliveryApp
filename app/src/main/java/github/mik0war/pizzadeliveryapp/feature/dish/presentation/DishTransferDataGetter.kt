package github.mik0war.pizzadeliveryapp.feature.dish.presentation

import android.view.View
import github.mik0war.pizzadeliveryapp.feature.dish.dishDataModel.DishUIModel
import github.mik0war.pizzadeliveryapp.recycler_list.presentation.TransferDataGetter

class DishTransferDataGetter :
    TransferDataGetter<DishUIModel, Int> {
    override fun getTransferData(uiModel: DishUIModel, view: View) =
        (uiModel as DishUIModel.Success).getCurrentPrice()
}