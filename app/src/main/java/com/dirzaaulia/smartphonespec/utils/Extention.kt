package com.dirzaaulia.smartphonespec.utils

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.shimmer
import com.google.accompanist.placeholder.placeholder

//Int
fun Int?.replaceIfNull(replacementValue: Int = 0): Int {
    if (this == null) {
        return replacementValue
    }
    return this
}

//List
fun <T> List<T>?.replaceIfNull(replacementValue: List<T> = emptyList()): List<T> {
    if (this == null) {
        return replacementValue
    }
    return this
}

//Modifier
fun Modifier.visible(visibility: Boolean): Modifier = this.then(alpha(if (visibility) 1f else 0f))

fun Modifier.shimmerPlaceholder(shape: Shape = RoundedCornerShape(12.dp)): Modifier = composed {
    this.then(
        placeholder(
            visible = true,
            shape = shape,
            color = MaterialTheme.colorScheme.surfaceVariant,
            highlight = PlaceholderHighlight.shimmer()
        )
    )
}

//String
fun String?.commonErrorMessageIfEmpty(): String {
    return if (this.isNullOrEmpty()) Constant.COMMON_ERROR_MESSAGE
    else this
}

fun String?.replaceIfNull(replacementValue: String = ""): String {
    if (this == null) {
        return replacementValue
    }
    return this
}
