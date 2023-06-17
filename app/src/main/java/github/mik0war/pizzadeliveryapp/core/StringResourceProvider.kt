package github.mik0war.entity

import android.content.Context
import androidx.annotation.StringRes
import javax.inject.Inject

interface StringResourceProvider {
    fun getString(@StringRes id: Int): String

    class Base @Inject constructor(private val context: Context) : StringResourceProvider {
        override fun getString(@StringRes id: Int) = context.getString(id)
    }
}