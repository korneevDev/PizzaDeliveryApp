package github.mik0war.pizzadeliveryapp.feature.tags.presentation

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import github.mik0war.pizzadeliveryapp.core.ColorResourceProvider
import github.mik0war.pizzadeliveryapp.R
import github.mik0war.pizzadeliveryapp.core.CustomTextView
import github.mik0war.pizzadeliveryapp.feature.tags.dataModel.Tag
import github.mik0war.pizzadeliveryapp.core.GetList

class TagsRecyclerViewAdapter(
    private val getList: GetList<Tag>,
    private val colorResourceProvider: ColorResourceProvider,
    private val onChangeClickListener: (tag: Tag) -> Unit,
    private val onErrorClickListener: (tag: Tag) -> Unit,
) : RecyclerView.Adapter<TagViewHolder>() {

    fun update() {
        val diffResult = getList.getDiffUtilResult()
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.tag_object, parent, false
        )
        return when (viewType) {
            0 -> TagViewHolder.Selected(colorResourceProvider, onChangeClickListener, view)
            1 -> TagViewHolder.Common(colorResourceProvider, onChangeClickListener, view)
            2 -> TagViewHolder.Error(colorResourceProvider, onErrorClickListener, view)
            else -> throw IllegalStateException()
        }
    }

    override fun getItemCount(): Int = getList.getList().size

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        holder.bind(getList.getList()[position])
    }

    override fun getItemViewType(position: Int): Int =
        when (getList.getList()[position]) {
            is Tag.Selected -> 0
            is Tag.Common -> 1
            is Tag.Error -> 2
        }
}

sealed class TagViewHolder(
    private val onClickListener: (tag: Tag) -> Unit = {},
    view: View
) : RecyclerView.ViewHolder(view) {
    private val nameView: CustomTextView = itemView.findViewById(R.id.objectName)
    open fun bind(tag: Tag) {
        nameView.set(tag.getTagName())
        nameView.setOnClickListener {
            onClickListener.invoke(tag)
        }
        nameView.backgroundTintList =
            ColorStateList.valueOf(getColor())
        nameView.setTextColor(getTextColor())
    }

    protected abstract fun getColor(): Int
    protected abstract fun getTextColor(): Int

    class Selected(
        private val colorResourceProvider: ColorResourceProvider,
        onClickListener: (tag: Tag) -> Unit,
        view: View
    ) : TagViewHolder(onClickListener, view) {
        override fun getColor() = colorResourceProvider.getColor(R.color.selected_background)
        override fun getTextColor() = colorResourceProvider.getColor(R.color.selected_red)
    }

    class Common(
        private val colorResourceProvider: ColorResourceProvider,
        onClickListener: (tag: Tag) -> Unit,
        view: View
    ) : TagViewHolder(onClickListener, view) {
        override fun getColor() = colorResourceProvider.getColor(R.color.light_gray_background)
        override fun getTextColor() = colorResourceProvider.getColor(R.color.black)
    }

    class Error(
        private val colorResourceProvider: ColorResourceProvider,
        onClickListener: (tag: Tag) -> Unit,
        view: View
    ) : TagViewHolder(onClickListener, view) {
        override fun bind(tag: Tag) {
            super.bind(tag)
        }

        override fun getColor() = colorResourceProvider.getColor(R.color.light_gray_background)
        override fun getTextColor() = colorResourceProvider.getColor(R.color.black_transparent_65)
    }
}
