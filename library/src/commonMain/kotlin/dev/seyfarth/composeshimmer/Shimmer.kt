package dev.seyfarth.composeshimmer

import androidx.compose.animation.core.EaseInCubic
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

fun Modifier.shimmerEffect(
    visible: Boolean = true,
    shape: Shape = RoundedCornerShape(4.dp),
    baseColor: Color = Color(0xFFE0E0E0),
    highlightColor: Color = Color(0xFFF5F5F5),
    durationMillis: Int = 2300,
    widthOfShadowBrush: Dp = 500.dp,
    shimmerTravel: Dp = 300.dp,
    angle: Float = 30f
): Modifier = composed {
    if (!visible) return@composed this

    val transparentBaseColor = baseColor.copy(alpha = 0.25f)
    val transparentHighlightColor = highlightColor.copy(alpha = 0.7f)

    val density = LocalDensity.current
    val gradientWidthPx = with(density) { widthOfShadowBrush.toPx() }

    val shimmerTravelPx = with(density) { shimmerTravel.toPx() }

    val transition = rememberInfiniteTransition(label = "GlobalShimmer")

    val translateAnimation by transition.animateFloat(
        initialValue = -gradientWidthPx,
        targetValue = shimmerTravelPx + gradientWidthPx,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = durationMillis,
                easing = EaseInCubic
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "GlobalShimmerOffset"
    )

    var layoutCoordinates by remember { mutableStateOf<LayoutCoordinates?>(null) }

    val angleRad = remember(angle) { angle * PI.toFloat() / 180f }
    val cosAngle = remember(angleRad) { cos(angleRad) }
    val sinAngle = remember(angleRad) { sin(angleRad) }

    this
        .clip(shape)
        .background(transparentBaseColor)
        .onGloballyPositioned { coordinates ->
            layoutCoordinates = coordinates
        }
        .drawWithCache {
            val colorStops = arrayOf(
                0.0f to Color.Transparent,
                0.3f to transparentHighlightColor.copy(alpha = 0.1f),
                0.5f to transparentHighlightColor,
                0.7f to transparentHighlightColor.copy(alpha = 0.1f),
                1.0f to Color.Transparent
            )

            onDrawWithContent {
                drawContent()

                val coords = layoutCoordinates ?: return@onDrawWithContent

                val globalX = coords.positionInRoot().x
                val dx = translateAnimation - globalX

                drawRect(
                    brush = Brush.linearGradient(
                        colorStops = colorStops,
                        start = Offset(dx, 0f),
                        end = Offset(
                            dx + gradientWidthPx * cosAngle,
                            gradientWidthPx * sinAngle
                        )
                    )
                )
            }
        }
}
