package com.challenge.get.base.utils

import androidx.compose.ui.graphics.Color


object HexToJetpackColor {
    fun getColor(colorString: String): Color {
        return Color(android.graphics.Color.parseColor(if (colorString == "#FFFFFF") "#000000" else colorString))
    }
}