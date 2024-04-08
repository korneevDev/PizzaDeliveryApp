package github.mik0war.pizzadeliveryapp.feature.dish.presentation

import android.app.Dialog
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import github.mik0war.pizzadeliveryapp.core.Communication
import github.mik0war.pizzadeliveryapp.core.DispatchersController
import github.mik0war.pizzadeliveryapp.core.GetList
import github.mik0war.pizzadeliveryapp.core.ObserveLiveData
import github.mik0war.pizzadeliveryapp.feature.dish.domain.DishGetBaseListUseCase
import github.mik0war.pizzadeliveryapp.feature.dish.domain.DishGetExtendedDataUseCase
import github.mik0war.pizzadeliveryapp.feature.dish.domain.DishMapper
import github.mik0war.pizzadeliveryapp.feature.tags.presentation.TagUIModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DishViewModel @Inject constructor(
    private val getDataUseCase: DishGetBaseListUseCase,
    private val getExtendedDataUseCase: DishGetExtendedDataUseCase,
    private val communication: Communication<DishUIModel>,
    private val mapperToUI: DishMapper<DishUIModel>,
    private val dialogConfigurator: DishDialogConfigurator,
    private val dispatchersController: DispatchersController
) : ViewModel(), GetList<DishUIModel>, ObserveLiveData<List<DishUIModel>> {

    private var fullData: List<DishUIModel>? = null
    fun showData() = viewModelScope.launch(dispatchersController.main()) {
        val data = getDataUseCase.getDishList().map { it.map(mapperToUI) }
        fullData = data
        communication.updateLivaData(data)
    }

    fun filter(tag: TagUIModel) {
        val newList = fullData?.filter {
            tag.isFits((it as DishUIModel.Base).getFilterTag())
        } ?: emptyList()

        communication.updateLivaData(newList)
    }

    fun getExtendedData(id: String, uiMapper: DishUIMapper, dialog: Dialog) =
        viewModelScope.launch(dispatchersController.main()) {
            val extendedData = getExtendedDataUseCase.getExtendedData(id)
            extendedData.map(uiMapper)
            dialogConfigurator.configureDialog(dialog, uiMapper.getDialogBinding()) {}.show()
        }

    override fun getList() = communication.getList()
    override fun getDiffUtilResult() = communication.getDiffUtilResult()

    override fun observe(owner: LifecycleOwner, observer: Observer<List<DishUIModel>>) =
        communication.observe(owner, observer)
}