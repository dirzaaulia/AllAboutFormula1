package com.dirzaaulia.smartphonespec.domain.model

import androidx.annotation.Keep
import com.dirzaaulia.smartphonespec.domain.Constant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class PhoneDetail(

    @SerialName("brand")
    val brand: String = Constant.EMPTY_STRING,

    @SerialName("phone_name")
    val phoneName: String = Constant.EMPTY_STRING,

    @SerialName("thumbnail")
    val thumbnail: String = Constant.EMPTY_STRING,

    @SerialName("phone_images")
    val phoneImages: List<String> = emptyList(),

    @SerialName("release_date")
    val releaseDate: String = Constant.EMPTY_STRING,

    @SerialName("dimension")
    val dimension: String = Constant.EMPTY_STRING,

    @SerialName("os")
    val os: String = Constant.EMPTY_STRING,

    @SerialName("storage")
    val storage: String = Constant.EMPTY_STRING,

    @SerialName("specifications")
    val specifications: List<Specification> = emptyList()
) {
    companion object {
        fun PhoneDetail.toMapDetail(): Map<String, String> {
            return mapOf(
                Constant.BRAND to brand,
                Constant.RELEASE_DATE to releaseDate,
                Constant.DIMENTION to dimension,
                Constant.OS to os,
                Constant.STORAGE to storage,
            )
        }
    }
}