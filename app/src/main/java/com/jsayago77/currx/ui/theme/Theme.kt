package com.jsayago77.currx.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val TechDarkColorScheme = darkColorScheme(
    primary = NeonEmerald,
    secondary = BrightGreen,
    tertiary = AccentGreen,
    background = CharcoalBlack,
    surface = SurfaceBlack,
    onPrimary = CharcoalBlack,
    onSecondary = CharcoalBlack,
    onTertiary = CharcoalBlack,
    onBackground = TextPrimary,
    onSurface = TextPrimary,
    surfaceVariant = GlassWhite,
    outline = GlassBorder
)

@Composable
fun CurrXTheme(
    darkTheme: Boolean = true, // Always dark
    dynamicColor: Boolean = false, // Disable dynamic color for consistent branding
    content: @Composable () -> Unit
) {
    val colorScheme = TechDarkColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
