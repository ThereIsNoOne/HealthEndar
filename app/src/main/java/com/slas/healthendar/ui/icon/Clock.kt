package com.slas.healthendar.ui.icon

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

@Composable
fun ClockIcon(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "nest_clock_farsight_analog",
            defaultWidth = 40.0.dp,
            defaultHeight = 40.0.dp,
            viewportWidth = 40.0f,
            viewportHeight = 40.0f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                stroke = null,
                strokeAlpha = 1f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(21.333f, 20.417f)
                lineToRelative(4.125f, 4.166f)
                quadToRelative(0.417f, 0.375f, 0.396f, 0.917f)
                quadToRelative(-0.021f, 0.542f, -0.396f, 0.917f)
                quadToRelative(-0.416f, 0.416f, -0.958f, 0.416f)
                reflectiveQuadToRelative(-0.917f, -0.416f)
                lineToRelative(-3.958f, -3.959f)
                quadToRelative(-0.5f, -0.458f, -0.708f, -1.041f)
                quadToRelative(-0.209f, -0.584f, -0.209f, -1.209f)
                verticalLineToRelative(-5.5f)
                quadToRelative(0f, -0.541f, 0.375f, -0.937f)
                reflectiveQuadToRelative(0.917f, -0.396f)
                quadToRelative(0.542f, 0f, 0.938f, 0.396f)
                quadToRelative(0.395f, 0.396f, 0.395f, 0.937f)
                close()
                moveTo(20f, 6.208f)
                quadToRelative(0.542f, 0f, 0.938f, 0.396f)
                quadToRelative(0.395f, 0.396f, 0.395f, 0.938f)
                verticalLineToRelative(0.833f)
                quadToRelative(0f, 0.542f, -0.395f, 0.938f)
                quadToRelative(-0.396f, 0.395f, -0.938f, 0.395f)
                quadToRelative(-0.542f, 0f, -0.917f, -0.395f)
                quadToRelative(-0.375f, -0.396f, -0.375f, -0.938f)
                verticalLineToRelative(-0.833f)
                quadToRelative(0f, -0.542f, 0.375f, -0.938f)
                quadToRelative(0.375f, -0.396f, 0.917f, -0.396f)
                close()
                moveTo(33.792f, 20f)
                quadToRelative(0f, 0.542f, -0.396f, 0.917f)
                reflectiveQuadToRelative(-0.938f, 0.375f)
                horizontalLineToRelative(-0.833f)
                quadToRelative(-0.542f, 0f, -0.937f, -0.375f)
                quadToRelative(-0.396f, -0.375f, -0.396f, -0.917f)
                reflectiveQuadToRelative(0.396f, -0.938f)
                quadToRelative(0.395f, -0.395f, 0.937f, -0.395f)
                horizontalLineToRelative(0.833f)
                quadToRelative(0.542f, 0f, 0.938f, 0.395f)
                quadToRelative(0.396f, 0.396f, 0.396f, 0.938f)
                close()
                moveTo(20f, 30.292f)
                quadToRelative(0.542f, 0f, 0.938f, 0.396f)
                quadToRelative(0.395f, 0.395f, 0.395f, 0.937f)
                verticalLineToRelative(0.833f)
                quadToRelative(0f, 0.542f, -0.395f, 0.938f)
                quadToRelative(-0.396f, 0.396f, -0.938f, 0.396f)
                quadToRelative(-0.542f, 0f, -0.917f, -0.396f)
                reflectiveQuadToRelative(-0.375f, -0.938f)
                verticalLineToRelative(-0.833f)
                quadToRelative(0f, -0.542f, 0.375f, -0.937f)
                quadToRelative(0.375f, -0.396f, 0.917f, -0.396f)
                close()
                moveTo(9.708f, 20f)
                quadToRelative(0f, 0.542f, -0.396f, 0.917f)
                quadToRelative(-0.395f, 0.375f, -0.937f, 0.375f)
                horizontalLineToRelative(-0.833f)
                quadToRelative(-0.542f, 0f, -0.938f, -0.375f)
                quadToRelative(-0.396f, -0.375f, -0.396f, -0.917f)
                reflectiveQuadToRelative(0.396f, -0.938f)
                quadToRelative(0.396f, -0.395f, 0.938f, -0.395f)
                horizontalLineToRelative(0.833f)
                quadToRelative(0.542f, 0f, 0.937f, 0.395f)
                quadToRelative(0.396f, 0.396f, 0.396f, 0.938f)
                close()
                moveTo(20f, 36.375f)
                quadToRelative(-3.375f, 0f, -6.375f, -1.292f)
                quadToRelative(-3f, -1.291f, -5.208f, -3.521f)
                quadToRelative(-2.209f, -2.229f, -3.5f, -5.208f)
                quadTo(3.625f, 23.375f, 3.625f, 20f)
                quadToRelative(0f, -3.417f, 1.292f, -6.396f)
                quadToRelative(1.291f, -2.979f, 3.521f, -5.208f)
                quadToRelative(2.229f, -2.229f, 5.208f, -3.5f)
                reflectiveQuadTo(20f, 3.625f)
                quadToRelative(3.417f, 0f, 6.396f, 1.292f)
                quadToRelative(2.979f, 1.291f, 5.208f, 3.5f)
                quadToRelative(2.229f, 2.208f, 3.5f, 5.187f)
                reflectiveQuadTo(36.375f, 20f)
                quadToRelative(0f, 3.375f, -1.292f, 6.375f)
                quadToRelative(-1.291f, 3f, -3.5f, 5.208f)
                quadToRelative(-2.208f, 2.209f, -5.187f, 3.5f)
                quadToRelative(-2.979f, 1.292f, -6.396f, 1.292f)
                close()
                moveToRelative(0f, -2.625f)
                quadToRelative(5.75f, 0f, 9.75f, -4.021f)
                reflectiveQuadToRelative(4f, -9.729f)
                quadToRelative(0f, -5.75f, -4f, -9.75f)
                reflectiveQuadToRelative(-9.75f, -4f)
                quadToRelative(-5.708f, 0f, -9.729f, 4f)
                quadToRelative(-4.021f, 4f, -4.021f, 9.75f)
                quadToRelative(0f, 5.708f, 4.021f, 9.729f)
                quadTo(14.292f, 33.75f, 20f, 33.75f)
                close()
                moveTo(20f, 20f)
                close()
            }
        }.build()
    }
}