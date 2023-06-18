package github.mik0war.pizzadeliveryapp.feature.tags.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import github.mik0war.dish.tags.presentation.TagsDiffUtilCallBack
import github.mik0war.pizzadeliveryapp.feature.tags.dataModel.Tag
import github.mik0war.recycler_list.presentation.GetList
import github.mik0war.recycler_list.presentation.ObserveLiveData
import javax.inject.Inject

interface TagsLiveData : ObserveLiveData<List<Tag>>,
    GetList<Tag> {
    fun updateTagsList(tagsList: List<Tag>)

    class Base @Inject constructor() : TagsLiveData {
        private val tagList = MutableLiveData<List<Tag>>()
        private var diffResult: DiffUtil.DiffResult =
            DiffUtil.calculateDiff(
                TagsDiffUtilCallBack(
            this.tagList.value ?: emptyList(),
            emptyList()
        )
            )

        override fun observe(owner: LifecycleOwner, observer: Observer<List<Tag>>) {
            tagList.observe(owner, observer)
        }

        override fun getList(): List<Tag> = tagList.value ?: emptyList()

        override fun getDiffUtilResult(): DiffUtil.DiffResult = diffResult

        override fun updateTagsList(tagsList: List<Tag>) {
            val callBack = TagsDiffUtilCallBack(
                this.tagList.value ?: emptyList(),
                tagsList
            )
            diffResult = DiffUtil.calculateDiff(callBack)
            tagList.value = tagsList
        }

    }
}