package com.dirzaaulia.smartphonespec.core.data.model

import com.dirzaaulia.smartphonespec.core.model.Brand
import com.dirzaaulia.smartphonespec.core.network.model.NetworkBrand

fun NetworkBrand.asExternalModel() = Brand(
    brandId = brandId,
    brandName = brandName,
    brandSlug = brandSlug,
    deviceCount = deviceCount,
    detail = detail
)