package com.sep.byteClub.ui.utils.extensions

import kotlin.math.roundToInt

fun Double.toCelcius() : Int {
    return ((this - 32) / 1.8f).roundToInt()
}