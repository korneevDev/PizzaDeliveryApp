package github.mik0war.pizzadeliveryapp.feature.tags.domain

import github.mik0war.entity.ExceptionHandler
import github.mik0war.entity.NoConnectionException
import github.mik0war.entity.ServiceUnavailableException
import github.mik0war.entity.StringResourceProvider
import github.mik0war.pizzadeliveryapp.R
import github.mik0war.pizzadeliveryapp.feature.tags.dataModel.Tag
import javax.inject.Inject

class TagExceptionHandler @Inject constructor(
    private val stringContext: StringResourceProvider
): ExceptionHandler<Tag> {
    override fun mapExceptionToModel(exception: Exception): Tag =
        when(exception){
            is NoConnectionException ->
                Tag.Error(stringContext.getString(R.string.error_message_no_connection))
            is ServiceUnavailableException ->
                Tag.Error(
                    stringContext.getString(R.string.error_message_service_unavailable)
                )
            else ->
                Tag.Error(
                    exception.message
                        ?: stringContext.getString(R.string.error_message_unknown_error)
                )
        }
}