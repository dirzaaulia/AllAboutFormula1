package com.dirzaaulia.smartphonespec.core.domain

import com.dirzaaulia.smartphonespec.core.common.result.Result
import com.dirzaaulia.smartphonespec.core.data.repository.search.SearchSmartphoneRepository
import com.dirzaaulia.smartphonespec.core.model.Phone
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchSmartphoneUseCase @Inject constructor(
    private val searchSmartphoneRepository: SearchSmartphoneRepository,
) {
    operator fun invoke(query: String): Flow<Result<List<Phone>>> {
        return searchSmartphoneRepository.searchSmartphone(query = query)
    }
}
