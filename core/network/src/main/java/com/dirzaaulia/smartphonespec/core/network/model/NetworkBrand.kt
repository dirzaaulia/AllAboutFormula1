package com.dirzaaulia.smartphonespec.core.network.model

import com.dirzaaulia.smartphonespec.utilities.Constant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkBrand(
    @SerialName("brand_id")
    val brandId: Int = Constant.ZERO,
    @SerialName("brand_name")
    val brandName: String = Constant.EMPTY_STRING,
    @SerialName("brand_slug")
    val brandSlug: String = Constant.EMPTY_STRING,
    @SerialName("device_count")
    val deviceCount: Int = Constant.ZERO,
    @SerialName("detail")
    val detail: String = Constant.EMPTY_STRING
)
