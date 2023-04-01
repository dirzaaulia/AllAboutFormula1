package com.dirzaaulia.smartphonespec.domain.model

import androidx.annotation.Keep
import com.dirzaaulia.smartphonespec.domain.Constant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Spec (

    @SerialName("key")
    val key: String = Constant.EMPTY_STRING,

    @SerialName("val")
    val value: List<String> = emptyList()
)