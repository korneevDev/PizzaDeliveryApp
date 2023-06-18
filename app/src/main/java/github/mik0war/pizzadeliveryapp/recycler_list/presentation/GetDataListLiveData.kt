package github.mik0war.pizzadeliveryapp.recycler_list.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import github.mik0war.pizzadeliveryapp.core.UIEntity
import github.mik0war.recycler_list.presentation.GetList
import github.mik0war.recycler_list.presentation.ObserveLiveData
import javax.inject.Inject

interface GetDataListLiveData<T: UIEntity<T>> : ObserveLiveData<List<T>>, GetList<T> {
    fun updateLivaData(dataList: List<T>)

    class Base<T: UIEntity<T>> @Inject constructor(): GetDataListLiveData<T> {
        private val dataList = MutableLiveData<List<T>>()
        private lateinit var diffResult: DiffUtil.DiffResult

        override fun updateLivaData(dataList: List<T>) {
            val callBack = GetDataListDiffUtilCallback(
                this.dataList.value ?: emptyList(),
                dataList
            )
            diffResult = DiffUtil.calculateDiff(callBack)
            this.dataList.value = dataList
        }

        override fun observe(
            owner: LifecycleOwner, observer: Observer<List<T>>
        ) {
            this.dataList.observe(owner, observer)
        }

        override fun getList(): List<T> = dataList.value ?: emptyList()

        override fun getDiffUtilResult() = diffResult
    }
}