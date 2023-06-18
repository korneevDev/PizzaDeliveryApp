package github.mik0war.pizzadeliveryapp.recycler_list.presentation

import android.view.View
import github.mik0war.pizzadeliveryapp.core.UIEntity

interface TransferDataGetter<S: UIEntity<S>, R> {
    fun getTransferData(uiModel: S, view: View): R
}
