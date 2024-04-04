package github.mik0war.pizzadeliveryapp.dish.presentation

import android.app.Dialog
import android.view.ViewGroup
import github.mik0war.pizzadeliveryapp.databinding.ExtendedDishObjectBinding

interface DishDialogConfigurator {
    fun configureDialog(
        dialog: Dialog,
        binding: ExtendedDishObjectBinding,
        onClickListener: (uiModel: DishUIModel) -> Unit
    ): Dialog

    class Base : DishDialogConfigurator {
        override fun configureDialog(
            dialog: Dialog,
            binding: ExtendedDishObjectBinding,
            onClickListener: (uiModel: DishUIModel) -> Unit
        ): Dialog {

            dialog.setContentView(binding.root)
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            return dialog
        }
    }
}