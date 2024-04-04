package github.mik0war.pizzadeliveryapp.core

import android.content.Context
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface StringResourceProvider {
    fun getString(@StringRes id: Int): String

    class Base @Inject constructor(@ApplicationContext private val context: Context) : StringResourceProvider {
        override fun getString(@StringRes id: Int) = context.getString(id)
    }
}