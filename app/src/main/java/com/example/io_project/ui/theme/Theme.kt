package com.example.compose
import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.example.io_project.ui.theme.AppTypography
import com.example.io_project.ui.theme.backgroundDark
import com.example.io_project.ui.theme.backgroundDarkHighContrast
import com.example.io_project.ui.theme.backgroundDarkMediumContrast
import com.example.io_project.ui.theme.backgroundLight
import com.example.io_project.ui.theme.backgroundLightHighContrast
import com.example.io_project.ui.theme.backgroundLightMediumContrast
import com.example.io_project.ui.theme.errorContainerDark
import com.example.io_project.ui.theme.errorContainerDarkHighContrast
import com.example.io_project.ui.theme.errorContainerDarkMediumContrast
import com.example.io_project.ui.theme.errorContainerLight
import com.example.io_project.ui.theme.errorContainerLightHighContrast
import com.example.io_project.ui.theme.errorContainerLightMediumContrast
import com.example.io_project.ui.theme.errorDark
import com.example.io_project.ui.theme.errorDarkHighContrast
import com.example.io_project.ui.theme.errorDarkMediumContrast
import com.example.io_project.ui.theme.errorLight
import com.example.io_project.ui.theme.errorLightHighContrast
import com.example.io_project.ui.theme.errorLightMediumContrast
import com.example.io_project.ui.theme.inverseOnSurfaceDark
import com.example.io_project.ui.theme.inverseOnSurfaceDarkHighContrast
import com.example.io_project.ui.theme.inverseOnSurfaceDarkMediumContrast
import com.example.io_project.ui.theme.inverseOnSurfaceLight
import com.example.io_project.ui.theme.inverseOnSurfaceLightHighContrast
import com.example.io_project.ui.theme.inverseOnSurfaceLightMediumContrast
import com.example.io_project.ui.theme.inversePrimaryDark
import com.example.io_project.ui.theme.inversePrimaryDarkHighContrast
import com.example.io_project.ui.theme.inversePrimaryDarkMediumContrast
import com.example.io_project.ui.theme.inversePrimaryLight
import com.example.io_project.ui.theme.inversePrimaryLightHighContrast
import com.example.io_project.ui.theme.inversePrimaryLightMediumContrast
import com.example.io_project.ui.theme.inverseSurfaceDark
import com.example.io_project.ui.theme.inverseSurfaceDarkHighContrast
import com.example.io_project.ui.theme.inverseSurfaceDarkMediumContrast
import com.example.io_project.ui.theme.inverseSurfaceLight
import com.example.io_project.ui.theme.inverseSurfaceLightHighContrast
import com.example.io_project.ui.theme.inverseSurfaceLightMediumContrast
import com.example.io_project.ui.theme.onBackgroundDark
import com.example.io_project.ui.theme.onBackgroundDarkHighContrast
import com.example.io_project.ui.theme.onBackgroundDarkMediumContrast
import com.example.io_project.ui.theme.onBackgroundLight
import com.example.io_project.ui.theme.onBackgroundLightHighContrast
import com.example.io_project.ui.theme.onBackgroundLightMediumContrast
import com.example.io_project.ui.theme.onErrorContainerDark
import com.example.io_project.ui.theme.onErrorContainerDarkHighContrast
import com.example.io_project.ui.theme.onErrorContainerDarkMediumContrast
import com.example.io_project.ui.theme.onErrorContainerLight
import com.example.io_project.ui.theme.onErrorContainerLightHighContrast
import com.example.io_project.ui.theme.onErrorContainerLightMediumContrast
import com.example.io_project.ui.theme.onErrorDark
import com.example.io_project.ui.theme.onErrorDarkHighContrast
import com.example.io_project.ui.theme.onErrorDarkMediumContrast
import com.example.io_project.ui.theme.onErrorLight
import com.example.io_project.ui.theme.onErrorLightHighContrast
import com.example.io_project.ui.theme.onErrorLightMediumContrast
import com.example.io_project.ui.theme.onPrimaryContainerDark
import com.example.io_project.ui.theme.onPrimaryContainerDarkHighContrast
import com.example.io_project.ui.theme.onPrimaryContainerDarkMediumContrast
import com.example.io_project.ui.theme.onPrimaryContainerLight
import com.example.io_project.ui.theme.onPrimaryContainerLightHighContrast
import com.example.io_project.ui.theme.onPrimaryContainerLightMediumContrast
import com.example.io_project.ui.theme.onPrimaryDark
import com.example.io_project.ui.theme.onPrimaryDarkHighContrast
import com.example.io_project.ui.theme.onPrimaryDarkMediumContrast
import com.example.io_project.ui.theme.onPrimaryLight
import com.example.io_project.ui.theme.onPrimaryLightHighContrast
import com.example.io_project.ui.theme.onPrimaryLightMediumContrast
import com.example.io_project.ui.theme.onSecondaryContainerDark
import com.example.io_project.ui.theme.onSecondaryContainerDarkHighContrast
import com.example.io_project.ui.theme.onSecondaryContainerDarkMediumContrast
import com.example.io_project.ui.theme.onSecondaryContainerLight
import com.example.io_project.ui.theme.onSecondaryContainerLightHighContrast
import com.example.io_project.ui.theme.onSecondaryContainerLightMediumContrast
import com.example.io_project.ui.theme.onSecondaryDark
import com.example.io_project.ui.theme.onSecondaryDarkHighContrast
import com.example.io_project.ui.theme.onSecondaryDarkMediumContrast
import com.example.io_project.ui.theme.onSecondaryLight
import com.example.io_project.ui.theme.onSecondaryLightHighContrast
import com.example.io_project.ui.theme.onSecondaryLightMediumContrast
import com.example.io_project.ui.theme.onSurfaceDark
import com.example.io_project.ui.theme.onSurfaceDarkHighContrast
import com.example.io_project.ui.theme.onSurfaceDarkMediumContrast
import com.example.io_project.ui.theme.onSurfaceLight
import com.example.io_project.ui.theme.onSurfaceLightHighContrast
import com.example.io_project.ui.theme.onSurfaceLightMediumContrast
import com.example.io_project.ui.theme.onSurfaceVariantDark
import com.example.io_project.ui.theme.onSurfaceVariantDarkHighContrast
import com.example.io_project.ui.theme.onSurfaceVariantDarkMediumContrast
import com.example.io_project.ui.theme.onSurfaceVariantLight
import com.example.io_project.ui.theme.onSurfaceVariantLightHighContrast
import com.example.io_project.ui.theme.onSurfaceVariantLightMediumContrast
import com.example.io_project.ui.theme.onTertiaryContainerDark
import com.example.io_project.ui.theme.onTertiaryContainerDarkHighContrast
import com.example.io_project.ui.theme.onTertiaryContainerDarkMediumContrast
import com.example.io_project.ui.theme.onTertiaryContainerLight
import com.example.io_project.ui.theme.onTertiaryContainerLightHighContrast
import com.example.io_project.ui.theme.onTertiaryContainerLightMediumContrast
import com.example.io_project.ui.theme.onTertiaryDark
import com.example.io_project.ui.theme.onTertiaryDarkHighContrast
import com.example.io_project.ui.theme.onTertiaryDarkMediumContrast
import com.example.io_project.ui.theme.onTertiaryLight
import com.example.io_project.ui.theme.onTertiaryLightHighContrast
import com.example.io_project.ui.theme.onTertiaryLightMediumContrast
import com.example.io_project.ui.theme.outlineDark
import com.example.io_project.ui.theme.outlineDarkHighContrast
import com.example.io_project.ui.theme.outlineDarkMediumContrast
import com.example.io_project.ui.theme.outlineLight
import com.example.io_project.ui.theme.outlineLightHighContrast
import com.example.io_project.ui.theme.outlineLightMediumContrast
import com.example.io_project.ui.theme.outlineVariantDark
import com.example.io_project.ui.theme.outlineVariantDarkHighContrast
import com.example.io_project.ui.theme.outlineVariantDarkMediumContrast
import com.example.io_project.ui.theme.outlineVariantLight
import com.example.io_project.ui.theme.outlineVariantLightHighContrast
import com.example.io_project.ui.theme.outlineVariantLightMediumContrast
import com.example.io_project.ui.theme.primaryContainerDark
import com.example.io_project.ui.theme.primaryContainerDarkHighContrast
import com.example.io_project.ui.theme.primaryContainerDarkMediumContrast
import com.example.io_project.ui.theme.primaryContainerLight
import com.example.io_project.ui.theme.primaryContainerLightHighContrast
import com.example.io_project.ui.theme.primaryContainerLightMediumContrast
import com.example.io_project.ui.theme.primaryDark
import com.example.io_project.ui.theme.primaryDarkHighContrast
import com.example.io_project.ui.theme.primaryDarkMediumContrast
import com.example.io_project.ui.theme.primaryLight
import com.example.io_project.ui.theme.primaryLightHighContrast
import com.example.io_project.ui.theme.primaryLightMediumContrast
import com.example.io_project.ui.theme.scrimDark
import com.example.io_project.ui.theme.scrimDarkHighContrast
import com.example.io_project.ui.theme.scrimDarkMediumContrast
import com.example.io_project.ui.theme.scrimLight
import com.example.io_project.ui.theme.scrimLightHighContrast
import com.example.io_project.ui.theme.scrimLightMediumContrast
import com.example.io_project.ui.theme.secondaryContainerDark
import com.example.io_project.ui.theme.secondaryContainerDarkHighContrast
import com.example.io_project.ui.theme.secondaryContainerDarkMediumContrast
import com.example.io_project.ui.theme.secondaryContainerLight
import com.example.io_project.ui.theme.secondaryContainerLightHighContrast
import com.example.io_project.ui.theme.secondaryContainerLightMediumContrast
import com.example.io_project.ui.theme.secondaryDark
import com.example.io_project.ui.theme.secondaryDarkHighContrast
import com.example.io_project.ui.theme.secondaryDarkMediumContrast
import com.example.io_project.ui.theme.secondaryLight
import com.example.io_project.ui.theme.secondaryLightHighContrast
import com.example.io_project.ui.theme.secondaryLightMediumContrast
import com.example.io_project.ui.theme.surfaceDark
import com.example.io_project.ui.theme.surfaceDarkHighContrast
import com.example.io_project.ui.theme.surfaceDarkMediumContrast
import com.example.io_project.ui.theme.surfaceLight
import com.example.io_project.ui.theme.surfaceLightHighContrast
import com.example.io_project.ui.theme.surfaceLightMediumContrast
import com.example.io_project.ui.theme.surfaceVariantDark
import com.example.io_project.ui.theme.surfaceVariantDarkHighContrast
import com.example.io_project.ui.theme.surfaceVariantDarkMediumContrast
import com.example.io_project.ui.theme.surfaceVariantLight
import com.example.io_project.ui.theme.surfaceVariantLightHighContrast
import com.example.io_project.ui.theme.surfaceVariantLightMediumContrast
import com.example.io_project.ui.theme.tertiaryContainerDark
import com.example.io_project.ui.theme.tertiaryContainerDarkHighContrast
import com.example.io_project.ui.theme.tertiaryContainerDarkMediumContrast
import com.example.io_project.ui.theme.tertiaryContainerLight
import com.example.io_project.ui.theme.tertiaryContainerLightHighContrast
import com.example.io_project.ui.theme.tertiaryContainerLightMediumContrast
import com.example.io_project.ui.theme.tertiaryDark
import com.example.io_project.ui.theme.tertiaryDarkHighContrast
import com.example.io_project.ui.theme.tertiaryDarkMediumContrast
import com.example.io_project.ui.theme.tertiaryLight
import com.example.io_project.ui.theme.tertiaryLightHighContrast
import com.example.io_project.ui.theme.tertiaryLightMediumContrast

private val lightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
//    surfaceDim = surfaceDimLight,
//    surfaceBright = surfaceBrightLight,
//    surfaceContainerLowest = surfaceContainerLowestLight,
//    surfaceContainerLow = surfaceContainerLowLight,
//    surfaceContainer = surfaceContainerLight,
//    surfaceContainerHigh = surfaceContainerHighLight,
//    surfaceContainerHighest = surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
//    surfaceDim = surfaceDimDark,
//    surfaceBright = surfaceBrightDark,
//    surfaceContainerLowest = surfaceContainerLowestDark,
//    surfaceContainerLow = surfaceContainerLowDark,
//    surfaceContainer = surfaceContainerDark,
//    surfaceContainerHigh = surfaceContainerHighDark,
//    surfaceContainerHighest = surfaceContainerHighestDark,
)

private val mediumContrastLightColorScheme = lightColorScheme(
    primary = primaryLightMediumContrast,
    onPrimary = onPrimaryLightMediumContrast,
    primaryContainer = primaryContainerLightMediumContrast,
    onPrimaryContainer = onPrimaryContainerLightMediumContrast,
    secondary = secondaryLightMediumContrast,
    onSecondary = onSecondaryLightMediumContrast,
    secondaryContainer = secondaryContainerLightMediumContrast,
    onSecondaryContainer = onSecondaryContainerLightMediumContrast,
    tertiary = tertiaryLightMediumContrast,
    onTertiary = onTertiaryLightMediumContrast,
    tertiaryContainer = tertiaryContainerLightMediumContrast,
    onTertiaryContainer = onTertiaryContainerLightMediumContrast,
    error = errorLightMediumContrast,
    onError = onErrorLightMediumContrast,
    errorContainer = errorContainerLightMediumContrast,
    onErrorContainer = onErrorContainerLightMediumContrast,
    background = backgroundLightMediumContrast,
    onBackground = onBackgroundLightMediumContrast,
    surface = surfaceLightMediumContrast,
    onSurface = onSurfaceLightMediumContrast,
    surfaceVariant = surfaceVariantLightMediumContrast,
    onSurfaceVariant = onSurfaceVariantLightMediumContrast,
    outline = outlineLightMediumContrast,
    outlineVariant = outlineVariantLightMediumContrast,
    scrim = scrimLightMediumContrast,
    inverseSurface = inverseSurfaceLightMediumContrast,
    inverseOnSurface = inverseOnSurfaceLightMediumContrast,
    inversePrimary = inversePrimaryLightMediumContrast,
//    surfaceDim = surfaceDimLightMediumContrast,
//    surfaceBright = surfaceBrightLightMediumContrast,
//    surfaceContainerLowest = surfaceContainerLowestLightMediumContrast,
//    surfaceContainerLow = surfaceContainerLowLightMediumContrast,
//    surfaceContainer = surfaceContainerLightMediumContrast,
//    surfaceContainerHigh = surfaceContainerHighLightMediumContrast,
//    surfaceContainerHighest = surfaceContainerHighestLightMediumContrast,
)

private val highContrastLightColorScheme = lightColorScheme(
    primary = primaryLightHighContrast,
    onPrimary = onPrimaryLightHighContrast,
    primaryContainer = primaryContainerLightHighContrast,
    onPrimaryContainer = onPrimaryContainerLightHighContrast,
    secondary = secondaryLightHighContrast,
    onSecondary = onSecondaryLightHighContrast,
    secondaryContainer = secondaryContainerLightHighContrast,
    onSecondaryContainer = onSecondaryContainerLightHighContrast,
    tertiary = tertiaryLightHighContrast,
    onTertiary = onTertiaryLightHighContrast,
    tertiaryContainer = tertiaryContainerLightHighContrast,
    onTertiaryContainer = onTertiaryContainerLightHighContrast,
    error = errorLightHighContrast,
    onError = onErrorLightHighContrast,
    errorContainer = errorContainerLightHighContrast,
    onErrorContainer = onErrorContainerLightHighContrast,
    background = backgroundLightHighContrast,
    onBackground = onBackgroundLightHighContrast,
    surface = surfaceLightHighContrast,
    onSurface = onSurfaceLightHighContrast,
    surfaceVariant = surfaceVariantLightHighContrast,
    onSurfaceVariant = onSurfaceVariantLightHighContrast,
    outline = outlineLightHighContrast,
    outlineVariant = outlineVariantLightHighContrast,
    scrim = scrimLightHighContrast,
    inverseSurface = inverseSurfaceLightHighContrast,
    inverseOnSurface = inverseOnSurfaceLightHighContrast,
    inversePrimary = inversePrimaryLightHighContrast,
//    surfaceDim = surfaceDimLightHighContrast,
//    surfaceBright = surfaceBrightLightHighContrast,
//    surfaceContainerLowest = surfaceContainerLowestLightHighContrast,
//    surfaceContainerLow = surfaceContainerLowLightHighContrast,
//    surfaceContainer = surfaceContainerLightHighContrast,
//    surfaceContainerHigh = surfaceContainerHighLightHighContrast,
//    surfaceContainerHighest = surfaceContainerHighestLightHighContrast,
)

private val mediumContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkMediumContrast,
    onPrimary = onPrimaryDarkMediumContrast,
    primaryContainer = primaryContainerDarkMediumContrast,
    onPrimaryContainer = onPrimaryContainerDarkMediumContrast,
    secondary = secondaryDarkMediumContrast,
    onSecondary = onSecondaryDarkMediumContrast,
    secondaryContainer = secondaryContainerDarkMediumContrast,
    onSecondaryContainer = onSecondaryContainerDarkMediumContrast,
    tertiary = tertiaryDarkMediumContrast,
    onTertiary = onTertiaryDarkMediumContrast,
    tertiaryContainer = tertiaryContainerDarkMediumContrast,
    onTertiaryContainer = onTertiaryContainerDarkMediumContrast,
    error = errorDarkMediumContrast,
    onError = onErrorDarkMediumContrast,
    errorContainer = errorContainerDarkMediumContrast,
    onErrorContainer = onErrorContainerDarkMediumContrast,
    background = backgroundDarkMediumContrast,
    onBackground = onBackgroundDarkMediumContrast,
    surface = surfaceDarkMediumContrast,
    onSurface = onSurfaceDarkMediumContrast,
    surfaceVariant = surfaceVariantDarkMediumContrast,
    onSurfaceVariant = onSurfaceVariantDarkMediumContrast,
    outline = outlineDarkMediumContrast,
    outlineVariant = outlineVariantDarkMediumContrast,
    scrim = scrimDarkMediumContrast,
    inverseSurface = inverseSurfaceDarkMediumContrast,
    inverseOnSurface = inverseOnSurfaceDarkMediumContrast,
    inversePrimary = inversePrimaryDarkMediumContrast,
//    surfaceDim = surfaceDimDarkMediumContrast,
//    surfaceBright = surfaceBrightDarkMediumContrast,
//    surfaceContainerLowest = surfaceContainerLowestDarkMediumContrast,
//    surfaceContainerLow = surfaceContainerLowDarkMediumContrast,
//    surfaceContainer = surfaceContainerDarkMediumContrast,
//    surfaceContainerHigh = surfaceContainerHighDarkMediumContrast,
//    surfaceContainerHighest = surfaceContainerHighestDarkMediumContrast,
)

private val highContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkHighContrast,
    onPrimary = onPrimaryDarkHighContrast,
    primaryContainer = primaryContainerDarkHighContrast,
    onPrimaryContainer = onPrimaryContainerDarkHighContrast,
    secondary = secondaryDarkHighContrast,
    onSecondary = onSecondaryDarkHighContrast,
    secondaryContainer = secondaryContainerDarkHighContrast,
    onSecondaryContainer = onSecondaryContainerDarkHighContrast,
    tertiary = tertiaryDarkHighContrast,
    onTertiary = onTertiaryDarkHighContrast,
    tertiaryContainer = tertiaryContainerDarkHighContrast,
    onTertiaryContainer = onTertiaryContainerDarkHighContrast,
    error = errorDarkHighContrast,
    onError = onErrorDarkHighContrast,
    errorContainer = errorContainerDarkHighContrast,
    onErrorContainer = onErrorContainerDarkHighContrast,
    background = backgroundDarkHighContrast,
    onBackground = onBackgroundDarkHighContrast,
    surface = surfaceDarkHighContrast,
    onSurface = onSurfaceDarkHighContrast,
    surfaceVariant = surfaceVariantDarkHighContrast,
    onSurfaceVariant = onSurfaceVariantDarkHighContrast,
    outline = outlineDarkHighContrast,
    outlineVariant = outlineVariantDarkHighContrast,
    scrim = scrimDarkHighContrast,
    inverseSurface = inverseSurfaceDarkHighContrast,
    inverseOnSurface = inverseOnSurfaceDarkHighContrast,
    inversePrimary = inversePrimaryDarkHighContrast,
//    surfaceDim = surfaceDimDarkHighContrast,
//    surfaceBright = surfaceBrightDarkHighContrast,
//    surfaceContainerLowest = surfaceContainerLowestDarkHighContrast,
//    surfaceContainerLow = surfaceContainerLowDarkHighContrast,
//    surfaceContainer = surfaceContainerDarkHighContrast,
//    surfaceContainerHigh = surfaceContainerHighDarkHighContrast,
//    surfaceContainerHighest = surfaceContainerHighestDarkHighContrast,
)

@Immutable
data class ColorFamily(
    val color: Color,
    val onColor: Color,
    val colorContainer: Color,
    val onColorContainer: Color
)

val unspecified_scheme = ColorFamily(
    Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified
)

@Composable
fun IO_ProjectTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colorScheme = when {
        darkTheme -> darkScheme
        else -> lightScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}

