package com.dirzaaulia.smartphonespec.core.data.repository.brands

import com.dirzaaulia.smartphonespec.core.common.network.Dispatcher
import com.dirzaaulia.smartphonespec.core.common.network.SmartphoneSpecDispatchers
import com.dirzaaulia.smartphonespec.core.common.result.Result
import com.dirzaaulia.smartphonespec.core.data.model.asExternalModel
import com.dirzaaulia.smartphonespec.core.network.source.NetworkDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SmartphoneBrandsRepositoryImpl @Inject constructor(
    @Dispatcher(SmartphoneSpecDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val network: NetworkDataSource,
) : SmartphoneBrandsRepository {
    override fun getSmartphoneBrands() = flow {
        emit(Result.Loading)
        try {
            val response = network.getSmartphoneBrands().map { it.asExternalModel() }
            emit(Result.Success(response))
        } catch (throwable: Throwable) {
            emit(Result.Error(throwable))
        }
    }.flowOn(ioDispatcher)

}