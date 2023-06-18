package github.mik0war.pizzadeliveryapp.core

import android.content.Context
import android.util.AttributeSet
import github.mik0war.entity.CustomTextViewInterface

class CustomButton : androidx.appcompat.widget.AppCompatButton, CustomTextViewInterface {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun set(content: String) {
        this.text = content
    }
}