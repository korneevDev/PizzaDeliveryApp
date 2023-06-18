package github.mik0war.recycler_list.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import github.mik0war.pizzadeliveryapp.core.CustomTextView
import github.mik0war.pizzadeliveryapp.R
import github.mik0war.pizzadeliveryapp.core.UIEntity
import github.mik0war.pizzadeliveryapp.recycler_list.presentation.TransferDataGetter

abstract class RecyclerViewAdapter<T : UIEntity<T>, E, V : View>(
    private val internetDataLiveData: GetList<T>,
    private val imageLoader: ImageLoader,
    private val transferDataGetter: TransferDataGetter<T, E>
) : RecyclerView.Adapter<ViewHolder<T, V>>() {

    var onSuccessClickListener: (name: E) -> Unit = {}
    var onErrorClickListener: (() -> Unit)? = null
    fun update() {
        val diffResult = internetDataLiveData.getDiffUtilResult()
        diffResult.dispatchUpdatesTo(this)
    }

    protected abstract fun errorClass(model: T): Boolean
    protected abstract fun getSuccessLayout(): Int

    protected abstract fun getClickableId(): Int
    protected open fun buildSuccessViewHolder(
        imageLoader: ImageLoader,
        buttonId: Int,
        view: View,
        onClickListener: (name: E) -> Unit,
        transferDataGetter: TransferDataGetter<T, E>
    ): ViewHolder.Success<T, E, V> =
        ViewHolder.Success(
            imageLoader,
            getClickableId(),
            view,
            onSuccessClickListener,
            transferDataGetter
        )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<T, V> {
        val emptyList = viewType == 0
        val layout = if (emptyList)
            R.layout.empty_list_object
        else
            getSuccessLayout()

        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)

        return if (emptyList)
            ViewHolder.Error(getClickableId(), view, onErrorClickListener)
        else buildSuccessViewHolder(
            imageLoader,
            getClickableId(),
            view,
            onSuccessClickListener,
            transferDataGetter
        )
    }

    override fun getItemCount() = internetDataLiveData.getList().size

    override fun onBindViewHolder(holder: ViewHolder<T, V>, position: Int) {
        holder.bind(internetDataLiveData.getList()[position])
    }

    override fun getItemViewType(position: Int) =
        if (errorClass(internetDataLiveData.getList()[position])) 0 else 1
}

sealed class ViewHolder<T : UIEntity<T>, V : View>(
    view: View
) : RecyclerView.ViewHolder(view) {
    private val nameView: CustomTextView = itemView.findViewById(R.id.objectName)
    open fun bind(uiModel: T) {
        uiModel.show(nameView)
    }


    open class Success<T : UIEntity<T>, E, V : View>(
        private val imageLoader: ImageLoader,
        buttonId: Int,
        view: View,
        private val onClickListener: (name: E) -> Unit,
        private val transferDataGetter: TransferDataGetter<T, E>
    ) : ViewHolder<T, V>(view) {
        private val imageView = itemView.findViewById<ImageView>(R.id.imageHolder)
        private val `object`: V = itemView.findViewById(buttonId)
        override fun bind(uiModel: T) {
            super.bind(uiModel)
            if(uiModel.getUrl().isNotEmpty())
                imageLoader.loadImage(uiModel.getUrl(), imageView)

            setOnObjectClickListener(`object`, uiModel)
        }

        private fun setOnObjectClickListener(view: View, uiModel: T){
            view.setOnClickListener {
                onClickListener.invoke(transferDataGetter.getTransferData(uiModel, it))
            }
        }
    }

    class Error<T : UIEntity<T>, V : View>(
        private val buttonId: Int,
        view: View,
        private val reloadListener: (() -> Unit)?
    ) : ViewHolder<T, V>(view) {
        private lateinit var `object`: V
        override fun bind(uiModel: T) {
            super.bind(uiModel)

            reloadListener?.let { lambda ->
                `object` = itemView.findViewById(buttonId)
                `object`.setOnClickListener {
                    lambda.invoke()
                }
            }
        }
    }
}