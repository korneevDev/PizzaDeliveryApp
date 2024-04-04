package github.mik0war.pizzadeliveryapp.dish.presentation

import android.app.Dialog
import android.view.ViewGroup
import github.mik0war.pizzadeliveryapp.databinding.ExtendedDishObjectBinding
import javax.inject.Inject

interface DishDialogConfigurator {
    fun configureDialog(
        dialog: Dialog,
        binding: ExtendedDishObjectBinding,
        onClickListener: (uiModel: DishUIModel) -> Unit
    ): Dialog

    class Base @Inject constructor() : DishDialogConfigurator {
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
    //TODO BUG WITH SECOND CREATING DIALOG
    //TODO CAN'T DISMISS DIALOG
}