package com.dirzaaulia.smartphonespec.core.domain

import com.dirzaaulia.smartphonespec.core.common.result.Result
import com.dirzaaulia.smartphonespec.core.data.repository.brands.SmartphoneBrandsRepository
import com.dirzaaulia.smartphonespec.core.model.Brand
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSmartphoneBrandsUseCase @Inject constructor(
    private val smartphoneBrandsRepository: SmartphoneBrandsRepository
) {
    operator fun invoke(): Flow<Result<List<Brand>>> {
        return smartphoneBrandsRepository.getSmartphoneBrands()
    }
}