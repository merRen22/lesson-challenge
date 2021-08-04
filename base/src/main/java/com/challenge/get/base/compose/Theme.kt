package com.challenge.get.base.compose

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val LightThemeColors = lightColors(
    primary = Primary,
    primaryVariant = PrimaryDark,
    secondary = Accent
)

@Composable
fun ChallengeTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = LightThemeColors,
        typography = ThemeTypography,
        content = content
    )
}