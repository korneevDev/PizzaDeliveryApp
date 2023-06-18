package github.mik0war.pizzadeliveryapp.feature.dish.domain

import github.mik0war.entity.ExceptionHandler
import github.mik0war.entity.NoConnectionException
import github.mik0war.entity.ServiceUnavailableException
import github.mik0war.entity.StringResourceProvider
import github.mik0war.pizzadeliveryapp.R
import github.mik0war.pizzadeliveryapp.feature.dish.dishDataModel.DishModel
import javax.inject.Inject

class DishExceptionHandler @Inject constructor(
    private val stringContext: StringResourceProvider
): ExceptionHandler<DishModel> {
    override fun mapExceptionToModel(exception: Exception): DishModel =
        when(exception){
            is NoConnectionException ->
                DishModel.Error(stringContext.getString(R.string.error_message_no_connection))
            is ServiceUnavailableException ->
                DishModel.Error(
                    stringContext.getString(R.string.error_message_service_unavailable)
                )
            else ->
                DishModel.Error(
                    exception.message
                        ?: stringContext.getString(R.string.error_message_unknown_error)
                )
        }
}