package github.mik0war.entity

import android.content.Context
import android.util.AttributeSet

class CustomTextView : CustomTextViewInterface, androidx.appcompat.widget.AppCompatTextView{
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun set(content: String) {
        text = content
    }
}