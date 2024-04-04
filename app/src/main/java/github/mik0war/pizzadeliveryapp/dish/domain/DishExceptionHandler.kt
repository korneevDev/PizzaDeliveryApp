package github.mik0war.pizzadeliveryapp.dish.domain

import github.mik0war.pizzadeliveryapp.core.ExceptionHandler
import github.mik0war.pizzadeliveryapp.core.NoConnectionException
import github.mik0war.pizzadeliveryapp.core.ServiceUnavailableException
import github.mik0war.pizzadeliveryapp.core.StringResourceProvider
import github.mik0war.pizzadeliveryapp.R
import javax.inject.Inject

class DishExceptionHandler @Inject constructor(
    private val stringContext: StringResourceProvider
) : ExceptionHandler<DishDataModel> {
    override fun mapExceptionToModel(exception: Exception): DishDataModel =
        when (exception) {
            is NoConnectionException ->
                DishDataModel.Error(
                    stringContext.getString(R.string.error_message_no_connection),
                    //TODO change picture
                    R.drawable.ic_dashboard_black_24dp
                )

            is ServiceUnavailableException ->
                DishDataModel.Error(
                    stringContext.getString(R.string.error_message_service_unavailable),
                    //TODO change picture
                    R.drawable.cart_icon
                )

            else ->
                DishDataModel.Error(
                    exception.message
                        ?: stringContext.getString(R.string.error_message_unknown_error),
                    //TODO change picture
                    R.drawable.ic_home_black_24dp
                )
        }
}