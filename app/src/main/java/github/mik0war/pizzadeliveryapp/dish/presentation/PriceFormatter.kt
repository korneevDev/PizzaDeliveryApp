package github.mik0war.pizzadeliveryapp.dish.presentation

import android.content.Context
import github.mik0war.pizzadeliveryapp.R

interface PriceFormatter {
    fun formatPrice(price: Int): String

    class Base(private val context: Context): PriceFormatter {
        override fun formatPrice(price: Int): String = context.getString(R.string.price_format, price)
    }
}