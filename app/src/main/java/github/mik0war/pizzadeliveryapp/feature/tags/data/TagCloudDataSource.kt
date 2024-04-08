package github.mik0war.pizzadeliveryapp.feature.tags.data

import github.mik0war.pizzadeliveryapp.feature.tags.data.retrofit.TagService
import github.mik0war.pizzadeliveryapp.feature.tags.domain.TagDataModel
import github.mik0war.pizzadeliveryapp.feature.tags.domain.TagMapper
import javax.inject.Inject

class TagCloudDataSource @Inject constructor(
    private val tagService: TagService,
    private val mapper: TagMapper<TagDataModel>
) {

    suspend fun getDataFromCloud(): List<TagDataModel> =
        tagService.getListObject().getTags().map {
            it.map(mapper)
        }
}