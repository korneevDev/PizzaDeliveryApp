package github.mik0war.pizzadeliveryapp.feature.dish.presentation

import android.app.Dialog
import android.view.View
import github.mik0war.pizzadeliveryapp.core.ImageLoader
import github.mik0war.pizzadeliveryapp.databinding.DishEmptyObjectBinding
import github.mik0war.pizzadeliveryapp.databinding.DishObjectBinding
import github.mik0war.pizzadeliveryapp.databinding.ExtendedDishObjectBinding
import github.mik0war.pizzadeliveryapp.feature.dish.domain.DishMapper
import github.mik0war.pizzadeliveryapp.feature.dish.domain.Ingredient
import javax.inject.Inject

interface UIMapper<T> : DishMapper<Unit> {
    fun getSuccessRoot(): View
    fun getDialogBinding(): T
    fun getErrorRoot(): View
    fun getExtendedRoot(): View
}

class DishUIMapper @Inject constructor(
    private val successBinding: DishObjectBinding,
    private val errorBinding: DishEmptyObjectBinding,
    private val extendedDishObjectBinding: ExtendedDishObjectBinding,
    private val imageLoader: ImageLoader,
    private val dialog: Dialog,
    private val dialogClickLambda: (dialog: Dialog, dishUIModel: DishUIModel, uiMapper: DishUIMapper) -> Unit
) : UIMapper<ExtendedDishObjectBinding> {
    override fun getSuccessRoot(): View = successBinding.root
    override fun getDialogBinding() = extendedDishObjectBinding

    override fun getErrorRoot(): View = errorBinding.root

    override fun getExtendedRoot(): View = extendedDishObjectBinding.root

    override fun mapSuccess(
        id: String,
        dishName: String,
        tag: String,
        imageURL: String,
        ingredients: List<Ingredient>
    ) {
        successBinding.objectName.set(dishName)
        extendedDishObjectBinding.objectName.set(dishName)
        var description = ""
        ingredients.forEach {
            description = it.addToDescription(description)
        }
        successBinding.descriptionView.set(description)

        imageLoader.loadImage(imageURL, successBinding.imageHolder)
        imageLoader.loadImage(imageURL, extendedDishObjectBinding.imageHolder)

        successBinding.root.setOnClickListener {
            dialog.dismiss()
            dialogClickLambda.invoke(
                dialog,
                DishUIModel.Base(id, dishName, tag, imageURL, ingredients),
                this
            )
        }

    }

    override fun mapError(errorName: String, errorImageId: Int) {
        errorBinding.objectName.set(errorName)
        errorBinding.imageHolder.setImageResource(errorImageId)
    }

    override fun mapExtendedData(
        id: String,
        area: String,
        instructions: String,
        youtubeLink: String,
        sourceLink: String
    ) {
        extendedDishObjectBinding.descriptionView.set(instructions)
        extendedDishObjectBinding.sourceLink.set(sourceLink)
        extendedDishObjectBinding.youtubeLink.set(youtubeLink)
        extendedDishObjectBinding.area.set(area)
    }
}