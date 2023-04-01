package com.dirzaaulia.smartphonespec.domain.data.model

import androidx.annotation.Keep
import com.dirzaaulia.smartphonespec.domain.data.Constant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Phone(

    @SerialName("phone_name")
    val phoneName: String = Constant.EMPTY_STRING,

    @SerialName("slug")
    val slug: String = Constant.EMPTY_STRING,

    @SerialName("image")
    val image: String = Constant.EMPTY_STRING,

    @SerialName("detail")
    val detail: String = Constant.EMPTY_STRING
)