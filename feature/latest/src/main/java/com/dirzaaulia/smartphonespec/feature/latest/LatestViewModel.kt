/*
 * Copyright 2023 Dirza Aulia
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dirzaaulia.smartphonespec.feature.latest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dirzaaulia.smartphonespec.core.common.result.Result
import com.dirzaaulia.smartphonespec.core.domain.GetLatestSmartphoneUseCase
import com.dirzaaulia.smartphonespec.core.model.Phone
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LatestViewModel @Inject constructor(
    private val getLatestSmartphoneUseCase: GetLatestSmartphoneUseCase,
) : ViewModel() {

    private val _latestPhoneUiState: MutableStateFlow<LatestPhoneUiState> =
        MutableStateFlow(LatestPhoneUiState.Loading)
    val latestPhoneUiState: StateFlow<LatestPhoneUiState> =
        _latestPhoneUiState.asStateFlow()

    init {
        setLatestPhoneUiState()
    }

    fun setLatestPhoneUiState() {
        getLatestSmartphoneUseCase()
            .onEach { result ->
                when (result) {
                    Result.Loading -> _latestPhoneUiState.value = LatestPhoneUiState.Loading
                    is Result.Success -> _latestPhoneUiState.value = LatestPhoneUiState.Success(
                        phones = result.data,
                    )
                    is Result.Error -> _latestPhoneUiState.value = LatestPhoneUiState.Error(
                        throwable = result.throwable,
                    )
                }
            }
            .launchIn(viewModelScope)
    }
}

sealed interface LatestPhoneUiState {
    object Loading : LatestPhoneUiState
    data class Success(val phones: List<Phone>) : LatestPhoneUiState
    data class Error(val throwable: Throwable) : LatestPhoneUiState
}
