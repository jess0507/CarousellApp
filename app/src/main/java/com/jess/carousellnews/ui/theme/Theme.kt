package com.jess.carousellnews.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView

private val DarkColorScheme = darkColorScheme(
    primary = Red200,
    secondary = Red200,
    background = BackgroundGray,
    surface = White,
    onPrimary = Red200,
    onSecondary = White,
    onBackground = Color.Black,
    onSurface = Color.Black,
)

private val LightColorScheme = lightColorScheme(
    primary = Red200,
    secondary = Red200,
    background = BackgroundGray,
    surface = White,
    onPrimary = White,
    onSecondary = White,
    onBackground = Color.Black,
    onSurface = Color.Black,
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val activity  = view.context as Activity
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                activity.window.statusBarColor = colorScheme.primary.toArgb()
                activity.window.navigationBarColor = colorScheme.onSecondary.toArgb()
            }
        }
    }


    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}