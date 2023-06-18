package github.mik0war.pizzadeliveryapp.feature.dish.presentation

import android.view.View
import android.widget.Button
import github.mik0war.pizzadeliveryapp.R
import github.mik0war.pizzadeliveryapp.core.CustomButton
import github.mik0war.pizzadeliveryapp.core.CustomTextView
import github.mik0war.pizzadeliveryapp.feature.dish.dishDataModel.DishUIModel
import github.mik0war.recycler_list.presentation.GetList
import github.mik0war.recycler_list.presentation.ImageLoader
import github.mik0war.recycler_list.presentation.RecyclerViewAdapter
import github.mik0war.pizzadeliveryapp.recycler_list.presentation.TransferDataGetter
import github.mik0war.recycler_list.presentation.ViewHolder

class CategoryRecyclerViewAdapter(
    internetDataLiveData: GetList<DishUIModel>,
    imageLoader: ImageLoader,
    transferDataGetter: DishTransferDataGetter,
    private val priceFormatter: PriceFormatter
) : RecyclerViewAdapter<DishUIModel, Int, Button>(
    internetDataLiveData,
    imageLoader,
    transferDataGetter
) {
    override fun errorClass(model: DishUIModel) = model is DishUIModel.Error
    override fun getSuccessLayout() = R.layout.dish_object
    override fun getClickableId() = R.id.priceView

    override fun buildSuccessViewHolder(
        imageLoader: ImageLoader,
        buttonId: Int,
        view: View,
        onClickListener: (name: Int) -> Unit,
        transferDataGetter: TransferDataGetter<DishUIModel, Int>
    ): ViewHolder.Success<DishUIModel, Int, Button> {
        return DishSuccessViewHolder(
            imageLoader,
            buttonId,
            view,
            onClickListener,
            transferDataGetter,
            priceFormatter
        )
    }
}

class DishSuccessViewHolder(
    imageLoader: ImageLoader,
    buttonId: Int,
    view: View,
    onClickListener: (name: Int) -> Unit,
    transferDataGetter: TransferDataGetter<DishUIModel, Int>,
    private val priceFormatter: PriceFormatter
) : ViewHolder.Success<DishUIModel, Int, Button>(
    imageLoader, buttonId, view, onClickListener, transferDataGetter
){
    private val priceView: CustomButton = itemView.findViewById(buttonId)
    private val descriptionView: CustomTextView = itemView.findViewById(R.id.descriptionView)

    override fun bind(uiModel: DishUIModel) {
        super.bind(uiModel)
        if(uiModel is DishUIModel.Success)
            uiModel.show(priceFormatter, priceView, descriptionView)
    }
}