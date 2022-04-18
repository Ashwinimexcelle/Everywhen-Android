package com.mexcelle.thoughtifydemo.util

import android.content.Context
import android.graphics.Typeface




object FontAwesomeManager {

    val ROOT = "fonts/"
    val FONTAWESOME = ROOT + "fa-solid-900.ttf"

    public fun getTypeface(context: Context, font: String?): Typeface? {
        return Typeface.createFromAsset(context.getAssets(), font)
    }
}