package github.mik0war.pizzadeliveryapp.feature.tags.domain

import github.mik0war.pizzadeliveryapp.R
import github.mik0war.pizzadeliveryapp.core.StringResourceProvider
import javax.inject.Inject

class TagGetListUseCase @Inject constructor(
    private val repository: TagRepository,
    private val stringResourceProvider: StringResourceProvider
) {
    suspend fun getDishList(): List<TagDataModel> =
        try {
            listOf(
                TagDataModel(
                    0,
                    stringResourceProvider.getString(R.string.tag_all),
                    true
                )
            ) + repository.getTagList()
        } catch (e: Exception) {
            emptyList()
        }

}