package github.mik0war.pizzadeliveryapp.dish.presentation

import android.app.Dialog
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import github.mik0war.pizzadeliveryapp.dish.domain.Communication
import github.mik0war.pizzadeliveryapp.core.DispatchersController
import github.mik0war.pizzadeliveryapp.core.GetList
import github.mik0war.pizzadeliveryapp.core.ObserveLiveData
import github.mik0war.pizzadeliveryapp.dish.domain.DishGetBaseListUseCase
import github.mik0war.pizzadeliveryapp.dish.domain.DishGetExtendedDataUseCase
import github.mik0war.pizzadeliveryapp.dish.domain.DishMapper
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

    fun showData() = viewModelScope.launch(dispatchersController.main()) {
        communication.updateLivaData(getDataUseCase.getDishList().map { it.map(mapperToUI) })
    }

    fun getExtendedData(id: String, uiMapper: DishUIMapper, dialog: Dialog) = viewModelScope.launch(dispatchersController.main()) {
        val extendedData = getExtendedDataUseCase.getExtendedData(id)
        extendedData.map(uiMapper)
        dialogConfigurator.configureDialog(dialog, uiMapper.getDialogBinding()){}.show()
    }
    override fun getList() = communication.getList()
    override fun getDiffUtilResult() = communication.getDiffUtilResult()

    override fun observe(owner: LifecycleOwner, observer: Observer<List<DishUIModel>>) =
        communication.observe(owner, observer)
}