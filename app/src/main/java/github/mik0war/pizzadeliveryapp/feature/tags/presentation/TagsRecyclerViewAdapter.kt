package github.mik0war.pizzadeliveryapp.feature.tags.presentation

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import github.mik0war.pizzadeliveryapp.R
import github.mik0war.pizzadeliveryapp.core.ColorResourceProvider
import github.mik0war.pizzadeliveryapp.core.GetList
import github.mik0war.pizzadeliveryapp.databinding.TagObjectBinding

class TagsRecyclerViewAdapter(
    private val getList: GetList<TagUIModel>,
    private val colorResourceProvider: ColorResourceProvider,
    private val onChangeClickListener: (tagUIModel: TagUIModel) -> Unit,
) : RecyclerView.Adapter<TagViewHolder>() {

    fun update() {
        val diffResult = getList.getDiffUtilResult()
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        val binding = TagObjectBinding.inflate(LayoutInflater.from(parent.context))
        return when (viewType) {
            0 -> TagViewHolder.Common(colorResourceProvider, onChangeClickListener, binding)
            1 -> TagViewHolder.Selected(colorResourceProvider, onChangeClickListener, binding)
            else -> throw IllegalStateException()
        }
    }

    override fun getItemCount(): Int = getList.getList().size

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        holder.bind(getList.getList()[position])
    }

    override fun getItemViewType(position: Int): Int =
        when (getList.getList()[position]) {
            is TagUIModel.Common -> 0
            is TagUIModel.Selected -> 1
        }
}

sealed class TagViewHolder(
    private val onClickListener: (tagUIModel: TagUIModel) -> Unit = {},
    private val binding: TagObjectBinding
) : RecyclerView.ViewHolder(binding.root) {
    open fun bind(tagUIModel: TagUIModel) {
        tagUIModel.show(binding.tag)
        binding.tag.setOnClickListener {
            onClickListener.invoke(tagUIModel)
        }
        binding.tag.backgroundTintList =
            ColorStateList.valueOf(getColor())
        binding.tag.setTextColor(getTextColor())
    }

    protected abstract fun getColor(): Int
    protected abstract fun getTextColor(): Int

    class Selected(
        private val colorResourceProvider: ColorResourceProvider,
        onClickListener: (tagUIModel: TagUIModel) -> Unit,
        binding: TagObjectBinding
    ) : TagViewHolder(onClickListener, binding) {
        override fun getColor() = colorResourceProvider.getColor(R.color.selected_background)
        override fun getTextColor() = colorResourceProvider.getColor(R.color.selected_red)
    }

    class Common(
        private val colorResourceProvider: ColorResourceProvider,
        onClickListener: (tagUIModel: TagUIModel) -> Unit,
        binding: TagObjectBinding
    ) : TagViewHolder(onClickListener, binding) {
        override fun getColor() = colorResourceProvider.getColor(R.color.light_gray_background)
        override fun getTextColor() = colorResourceProvider.getColor(R.color.black)
    }
}
