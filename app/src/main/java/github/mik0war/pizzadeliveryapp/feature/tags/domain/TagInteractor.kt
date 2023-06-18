package github.mik0war.pizzadeliveryapp.feature.tags.domain

import github.mik0war.entity.ExceptionHandler
import github.mik0war.pizzadeliveryapp.core.DataMapper
import github.mik0war.pizzadeliveryapp.feature.tags.dataModel.Tag
import github.mik0war.pizzadeliveryapp.feature.tags.dataModel.TagDataModel
import github.mik0war.pizzadeliveryapp.recycler_list.domain.GetDataListRepository
import javax.inject.Inject

interface TagInteractor {
    suspend fun getTagsList(): List<Tag>
    fun getTagsList(oldTagsList: List<Tag>): List<Tag>
    fun updateTag(tagsList: List<Tag>, tag: Tag): List<Tag>

    class Base @Inject constructor(
        private val repository: GetDataListRepository<TagDataModel>,
        private val mapper: DataMapper<TagDataModel, Tag>,
        private val exceptionHandler: ExceptionHandler<Tag>
    ) : TagInteractor {

        override suspend fun getTagsList(): List<Tag> =
            try {
               repository.getObjectsList()
                    .map { mapper.map(it) }.sortedBy {
                        it.getTagId()
                   }
            } catch (e: Exception) {
                listOf(exceptionHandler.mapExceptionToModel(e))
            }


        override fun getTagsList(oldTagsList: List<Tag>): List<Tag> {
            val tags = mutableListOf<Tag>()

            oldTagsList.forEach {
                when(it){
                    is Tag.Common -> tags.add(it)
                    is Tag.NewSelected -> tags.add(Tag.Selected(it.getTagId(), it.getTagName()))
                    is Tag.Selected -> tags.add(Tag.Common(it.getTagId(), it.getTagName()))
                    is Tag.Error -> tags.add(it)
                }
            }

            return tags.toList()
        }

        override fun updateTag(tagsList: List<Tag>, tag: Tag): List<Tag> {
            val mutableList = tagsList.toMutableList()

            val position = tagsList.indexOf(tag)
            mutableList.remove(tag)
            mutableList.add(position, Tag.NewSelected(tag.getTagId(), tag.getTagName()))

            return getTagsList(mutableList.toList())
        }
    }
}
