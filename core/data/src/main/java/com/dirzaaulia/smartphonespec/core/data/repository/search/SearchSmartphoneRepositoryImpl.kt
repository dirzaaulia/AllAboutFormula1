package com.dirzaaulia.smartphonespec.core.data.repository.search

import com.dirzaaulia.smartphonespec.core.common.network.Dispatcher
import com.dirzaaulia.smartphonespec.core.common.network.SmartphoneSpecDispatchers
import com.dirzaaulia.smartphonespec.core.common.result.Result
import com.dirzaaulia.smartphonespec.core.data.model.asExternalModel
import com.dirzaaulia.smartphonespec.core.network.source.NetworkDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SearchSmartphoneRepositoryImpl @Inject constructor(
    @Dispatcher(SmartphoneSpecDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val network: NetworkDataSource,
) : SearchSmartphoneRepository {

    override fun searchSmartphone(query: String) = flow {
        emit(Result.Loading)
        try {
            val response = network.searchSmartphone(query = query)
                .map { it.asExternalModel() }
            emit(Result.Success(response))
        } catch (throwable: Throwable) {
            emit(Result.Error(throwable))
        }
    }.flowOn(ioDispatcher)
}
