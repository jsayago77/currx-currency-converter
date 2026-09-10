package com.jsayago77.currx.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.colorResource
import androidx.core.view.WindowCompat
import com.jsayago77.currx.R

@Composable
fun CurrXTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        darkColorScheme(
            primary = colorResource(R.color.neon_emerald),
            secondary = colorResource(R.color.bright_green),
            tertiary = colorResource(R.color.deep_green),
            background = colorResource(R.color.charcoal_black),
            surface = colorResource(R.color.surface_black),
            onPrimary = colorResource(R.color.charcoal_black),
            onSecondary = colorResource(R.color.charcoal_black),
            onTertiary = colorResource(R.color.white),
            onBackground = colorResource(R.color.text_primary_dark),
            onSurface = colorResource(R.color.text_primary_dark),
            outline = colorResource(R.color.glass_border)
        )
    } else {
        lightColorScheme(
            primary = colorResource(R.color.bright_green),
            secondary = colorResource(R.color.deep_green),
            tertiary = colorResource(R.color.dark_green),
            background = colorResource(R.color.soft_white),
            surface = colorResource(R.color.surface_white),
            onPrimary = colorResource(R.color.white),
            onSecondary = colorResource(R.color.white),
            onTertiary = colorResource(R.color.white),
            onBackground = colorResource(R.color.text_primary_light),
            onSurface = colorResource(R.color.text_primary_light),
            outline = colorResource(R.color.glass_border_dark)
        )
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
