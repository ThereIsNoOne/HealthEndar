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
fun rememberAlarmAdd(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "alarm_add",
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
                moveTo(20f, 28.083f)
                quadToRelative(0.542f, 0f, 0.938f, -0.375f)
                quadToRelative(0.395f, -0.375f, 0.395f, -0.916f)
                verticalLineToRelative(-3.834f)
                horizontalLineToRelative(3.792f)
                quadToRelative(0.542f, 0f, 0.917f, -0.396f)
                quadToRelative(0.375f, -0.395f, 0.375f, -0.979f)
                quadToRelative(0f, -0.541f, -0.355f, -0.916f)
                quadToRelative(-0.354f, -0.375f, -0.895f, -0.375f)
                horizontalLineToRelative(-3.834f)
                verticalLineToRelative(-3.834f)
                quadToRelative(0f, -0.5f, -0.395f, -0.895f)
                quadToRelative(-0.396f, -0.396f, -0.938f, -0.396f)
                quadToRelative(-0.542f, 0f, -0.917f, 0.375f)
                reflectiveQuadToRelative(-0.375f, 0.875f)
                verticalLineToRelative(3.875f)
                horizontalLineToRelative(-3.791f)
                quadToRelative(-0.584f, 0f, -0.959f, 0.375f)
                reflectiveQuadToRelative(-0.375f, 0.916f)
                quadToRelative(0f, 0.584f, 0.355f, 0.979f)
                quadToRelative(0.354f, 0.396f, 0.895f, 0.396f)
                horizontalLineToRelative(3.875f)
                verticalLineToRelative(3.792f)
                quadToRelative(0f, 0.583f, 0.375f, 0.958f)
                reflectiveQuadToRelative(0.917f, 0.375f)
                close()
                moveToRelative(0f, 8.25f)
                quadToRelative(-3.042f, 0f, -5.729f, -1.145f)
                quadToRelative(-2.688f, -1.146f, -4.667f, -3.146f)
                quadToRelative(-1.979f, -2f, -3.146f, -4.667f)
                quadToRelative(-1.166f, -2.667f, -1.166f, -5.75f)
                quadToRelative(0f, -3.042f, 1.146f, -5.729f)
                quadToRelative(1.145f, -2.688f, 3.145f, -4.688f)
                quadToRelative(2f, -2f, 4.688f, -3.166f)
                quadTo(16.958f, 6.875f, 20f, 6.875f)
                quadToRelative(3.042f, 0f, 5.729f, 1.167f)
                quadToRelative(2.688f, 1.166f, 4.688f, 3.166f)
                quadToRelative(2f, 2f, 3.145f, 4.688f)
                quadToRelative(1.146f, 2.687f, 1.146f, 5.729f)
                quadToRelative(0f, 3.083f, -1.146f, 5.75f)
                quadToRelative(-1.145f, 2.667f, -3.145f, 4.667f)
                reflectiveQuadToRelative(-4.688f, 3.146f)
                quadTo(23.042f, 36.333f, 20f, 36.333f)
                close()
                moveToRelative(0f, -14.666f)
                close()
                moveTo(5.208f, 11.708f)
                quadToRelative(-0.416f, 0.375f, -0.937f, 0.375f)
                quadToRelative(-0.521f, 0f, -0.896f, -0.375f)
                quadToRelative(-0.417f, -0.416f, -0.417f, -0.958f)
                reflectiveQuadToRelative(0.417f, -0.917f)
                lineToRelative(4.917f, -4.791f)
                quadToRelative(0.375f, -0.375f, 0.916f, -0.375f)
                quadToRelative(0.542f, 0f, 0.917f, 0.375f)
                quadToRelative(0.375f, 0.416f, 0.375f, 0.958f)
                reflectiveQuadToRelative(-0.375f, 0.917f)
                close()
                moveToRelative(29.584f, -0.041f)
                lineToRelative(-4.917f, -4.792f)
                quadTo(29.5f, 6.542f, 29.479f, 6f)
                quadToRelative(-0.021f, -0.542f, 0.396f, -0.917f)
                quadToRelative(0.375f, -0.375f, 0.917f, -0.375f)
                quadToRelative(0.541f, 0f, 0.916f, 0.334f)
                lineToRelative(4.959f, 4.833f)
                quadToRelative(0.375f, 0.375f, 0.375f, 0.896f)
                reflectiveQuadToRelative(-0.375f, 0.896f)
                quadToRelative(-0.375f, 0.375f, -0.917f, 0.375f)
                reflectiveQuadToRelative(-0.958f, -0.375f)
                close()
                moveTo(20f, 33.708f)
                quadToRelative(5.042f, 0f, 8.562f, -3.52f)
                quadToRelative(3.521f, -3.521f, 3.521f, -8.563f)
                quadToRelative(0f, -5.042f, -3.521f, -8.562f)
                quadTo(25.042f, 9.542f, 20f, 9.542f)
                reflectiveQuadToRelative(-8.562f, 3.521f)
                quadToRelative(-3.521f, 3.52f, -3.521f, 8.562f)
                reflectiveQuadToRelative(3.521f, 8.563f)
                quadToRelative(3.52f, 3.52f, 8.562f, 3.52f)
                close()
            }
        }.build()
    }
}
