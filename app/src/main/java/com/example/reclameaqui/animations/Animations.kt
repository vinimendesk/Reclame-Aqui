package com.example.reclameaqui.animations

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.keyframes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember

// Animação de shake.
@Composable
fun shakeAnimation(trigger: Boolean): Float {
    val offsetX = remember { Animatable(0f) }

    if (trigger) {
        LaunchedEffect(Unit) {
            offsetX.animateTo(
                targetValue = 0f,
                animationSpec = keyframes {
                    durationMillis = 400
                    -10f at 0
                    10f at 50
                    -10f at 100
                    10f at 150
                    -10f at 200
                    6f at 250
                    -6f at 300
                    3f at 350
                    0f at 400
                }
            )
        }
    }

    return offsetX.value
}
