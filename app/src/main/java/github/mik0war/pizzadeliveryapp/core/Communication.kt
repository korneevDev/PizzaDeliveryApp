package github.mik0war.pizzadeliveryapp.core

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import javax.inject.Inject

interface Communication<T> : ObserveLiveData<List<T>>, GetList<T> {
    fun updateLivaData(dataList: List<T>)

    class Base<T : UIModel> @Inject constructor(): Communication<T> {
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
            dataList.observe(owner, observer)
        }

        override fun getList(): List<T> = dataList.value ?: emptyList()

        override fun getDiffUtilResult() = diffResult
    }
}