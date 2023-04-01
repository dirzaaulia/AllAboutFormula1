package com.dirzaaulia.smartphonespec.domain.model

import androidx.annotation.Keep
import com.dirzaaulia.smartphonespec.domain.Constant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class LatestPhones(

    @SerialName("title")
    val title: String = Constant.EMPTY_STRING,

    @SerialName("phones")
    val phones: List<Phone> = emptyList()
)