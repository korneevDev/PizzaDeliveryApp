package github.mik0war.pizzadeliveryapp.core

import android.content.Context
import androidx.annotation.ColorRes
import javax.inject.Inject


interface ColorResourceProvider {
    fun getColor(@ColorRes id: Int): Int

    class Base @Inject constructor(private val context: Context) : ColorResourceProvider {
        override fun getColor(@ColorRes id: Int) = context.getColor(id)
    }
}