package github.mik0war.pizzadeliveryapp.feature.tags.data.cloud

import github.mik0war.pizzadeliveryapp.core.DataMapper
import github.mik0war.pizzadeliveryapp.feature.tags.dataModel.TagDataModel
import github.mik0war.pizzadeliveryapp.recycler_list.data.CloudGetDataListDataSource
import javax.inject.Inject

class TagCloudGetDataListDataSource @Inject constructor(
    private val tagService: TagService,
    private val mapper: DataMapper<TagServerModel, TagDataModel>
) : CloudGetDataListDataSource.Base<TagDataModel>() {

    override suspend fun getDataFromCloud(): List<TagDataModel> =
        tagService.getListObject().getList().map { mapper.map(it)}
}