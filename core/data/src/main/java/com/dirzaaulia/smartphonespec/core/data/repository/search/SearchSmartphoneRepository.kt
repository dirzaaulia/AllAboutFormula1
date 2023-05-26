package com.dirzaaulia.smartphonespec.core.data.repository.search

import com.dirzaaulia.smartphonespec.core.common.result.Result
import com.dirzaaulia.smartphonespec.core.model.Phone
import kotlinx.coroutines.flow.Flow

interface SearchSmartphoneRepository {

    fun searchSmartphone(query: String): Flow<Result<List<Phone>>>
}