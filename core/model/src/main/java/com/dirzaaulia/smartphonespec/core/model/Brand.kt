package com.dirzaaulia.smartphonespec.core.model

data class Brand(
    val brandId: Int = Constant.ZERO,
    val brandName: String = Constant.EMPTY_STRING,
    val brandSlug: String = Constant.EMPTY_STRING,
    val deviceCount: Int = Constant.ZERO,
    val detail: String = Constant.EMPTY_STRING
)