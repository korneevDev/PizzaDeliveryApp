package github.mik0war.pizzadeliveryapp.feature.tags.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.DiffUtil
import github.mik0war.pizzadeliveryapp.feature.tags.dataModel.Tag
import github.mik0war.pizzadeliveryapp.feature.tags.domain.TagInteractor
import github.mik0war.recycler_list.presentation.GetList
import github.mik0war.recycler_list.presentation.ObserveLiveData
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

interface TagsViewModel: ObserveLiveData<List<Tag>>,
    GetList<Tag> {
    fun setTagsList() : Job
    fun updateTag(tag: Tag)


    class Base @Inject constructor(
        private val interactor: TagInteractor,
        private val liveData: TagsLiveData
    ): ViewModel(), TagsViewModel {
        override fun setTagsList() = viewModelScope.launch {
            liveData.updateTagsList(interactor.getTagsList())
            if(liveData.getList().isNotEmpty() && liveData.getList()[0] !is Tag.Error)
                updateTag(liveData.getList()[0])
        }

        override fun updateTag(tag: Tag) {
            liveData.updateTagsList(interactor.updateTag(liveData.getList(), tag))
        }


        override fun observe(owner: LifecycleOwner, observer: Observer<List<Tag>>) {
            liveData.observe(owner, observer)
        }

        override fun getList(): List<Tag> = liveData.getList()

        override fun getDiffUtilResult(): DiffUtil.DiffResult = liveData.getDiffUtilResult()
    }
}