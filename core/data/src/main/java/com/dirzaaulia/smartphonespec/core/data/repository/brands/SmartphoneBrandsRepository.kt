package com.dirzaaulia.smartphonespec.core.data.repository.brands

import com.dirzaaulia.smartphonespec.core.common.result.Result
import com.dirzaaulia.smartphonespec.core.model.Brand
import kotlinx.coroutines.flow.Flow

interface SmartphoneBrandsRepository {

    fun getSmartphoneBrands(): Flow<Result<List<Brand>>>
}