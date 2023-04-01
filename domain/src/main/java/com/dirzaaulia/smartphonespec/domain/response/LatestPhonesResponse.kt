package com.dirzaaulia.smartphonespec.domain.response

import androidx.annotation.Keep
import com.dirzaaulia.smartphonespec.domain.model.LatestPhones
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class LatestPhonesResponse (

    @SerialName("status")
    val status: Boolean = false,

    @SerialName("data")
    val data: LatestPhones? = null
)