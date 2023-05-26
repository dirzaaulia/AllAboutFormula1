package com.dirzaaulia.smartphonespec.core.data.repository.interest

import com.dirzaaulia.smartphonespec.core.common.result.Result
import com.dirzaaulia.smartphonespec.core.model.Phone
import kotlinx.coroutines.flow.Flow

interface TopByInterestSmartphoneRepository {
    fun getTopByInterestSmartphone(): Flow<Result<List<Phone>>>
}