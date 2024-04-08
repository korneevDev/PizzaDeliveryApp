package github.mik0war.pizzadeliveryapp.feature.tags.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import github.mik0war.pizzadeliveryapp.core.Communication
import github.mik0war.pizzadeliveryapp.core.DispatchersController
import github.mik0war.pizzadeliveryapp.core.GetList
import github.mik0war.pizzadeliveryapp.core.ObserveLiveData
import github.mik0war.pizzadeliveryapp.feature.tags.domain.TagGetListUseCase
import github.mik0war.pizzadeliveryapp.feature.tags.domain.TagMapper
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TagViewModel @Inject constructor(
    private val getDataUseCase: TagGetListUseCase,
    private val communication: Communication<TagUIModel>,
    private val mapperToUI: TagMapper<TagUIModel>,
    private val dispatchersController: DispatchersController
) : ViewModel(), GetList<TagUIModel>, ObserveLiveData<List<TagUIModel>> {

    private var selected: TagUIModel? = null
    fun performClick(tagUIModel: TagUIModel) {
        val newList = communication.getList()
            .map {
                if (tagUIModel != selected &&
                    (it == tagUIModel || it == selected)
                )
                    it.click()
                else it
            }
        selected = tagUIModel
        communication.updateLivaData(newList)
    }

    fun showData() = viewModelScope.launch(dispatchersController.main()) {
        val data = getDataUseCase.getDishList().map { it.map(mapperToUI) }.toMutableList()
        communication.updateLivaData(data)
        if(data.isNotEmpty())
            performClick(data[0])
    }

    override fun getList() = communication.getList()
    override fun getDiffUtilResult() = communication.getDiffUtilResult()

    override fun observe(owner: LifecycleOwner, observer: Observer<List<TagUIModel>>) =
        communication.observe(owner, observer)
}