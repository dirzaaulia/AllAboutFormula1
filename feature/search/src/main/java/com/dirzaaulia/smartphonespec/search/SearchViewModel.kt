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

package com.dirzaaulia.smartphonespec.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dirzaaulia.smartphonespec.core.common.result.Result
import com.dirzaaulia.smartphonespec.core.domain.GetSearchSmartphoneUseCase
import com.dirzaaulia.smartphonespec.core.domain.GetSmartphoneBrandsUseCase
import com.dirzaaulia.smartphonespec.core.model.Brand
import com.dirzaaulia.smartphonespec.core.model.Phone
import com.dirzaaulia.smartphonespec.utilities.Constant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSmartphoneBrandsUseCase: GetSmartphoneBrandsUseCase,
    private val getSearchSmartphoneUseCase: GetSearchSmartphoneUseCase
) : ViewModel() {

    var searchQuery = Constant.EMPTY_STRING

    private val _brandsUiState: MutableStateFlow<BrandsUiState> =
        MutableStateFlow(BrandsUiState.Loading)
    val brandsUiState: StateFlow<BrandsUiState> =
        _brandsUiState.asStateFlow()

    private val _searchUiState: MutableStateFlow<SearchUiState> =
        MutableStateFlow(SearchUiState.Loading)
    val searchUiState: StateFlow<SearchUiState> =
        _searchUiState.asStateFlow()

    init {
        setBrandsUiState()
    }

    internal fun setBrandsUiState() {
        getSmartphoneBrandsUseCase()
            .onEach { result ->
                _brandsUiState.value = when (result) {
                    Result.Loading -> BrandsUiState.Loading
                    is Result.Success -> BrandsUiState.Success(brands = result.data)
                    is Result.Error -> BrandsUiState.Error(throwable = result.throwable)
                }
            }
            .launchIn(viewModelScope)
    }

    internal fun setSearchUiState() {
        getSearchSmartphoneUseCase(query = searchQuery)
            .onEach { result ->
                _searchUiState.value = when (result) {
                    Result.Loading -> SearchUiState.Loading
                    is Result.Success -> SearchUiState.Success(phones = result.data)
                    is Result.Error -> SearchUiState.Error(throwable = result.throwable)
                }
            }
            .launchIn(viewModelScope)
    }
}

sealed interface BrandsUiState {
    object Loading : BrandsUiState
    data class Success(val brands: List<Brand>) : BrandsUiState
    data class Error(val throwable: Throwable) : BrandsUiState
}

sealed interface SearchUiState {
    object Loading : SearchUiState
    data class Success(val phones: List<Phone>) : SearchUiState
    data class Error(val throwable: Throwable) : SearchUiState
}
