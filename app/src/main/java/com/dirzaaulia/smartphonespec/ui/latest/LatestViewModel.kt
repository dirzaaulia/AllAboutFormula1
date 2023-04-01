package com.dirzaaulia.smartphonespec.ui.latest

import androidx.lifecycle.ViewModel
import com.dirzaaulia.smartphonespec.domain.response.LatestPhonesResponse
import com.dirzaaulia.smartphonespec.utils.ResponseResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LatestViewModel @Inject constructor(
): ViewModel() {

    private val _latestPhonesState: MutableStateFlow<ResponseResult<LatestPhonesResponse?>> =
        MutableStateFlow(ResponseResult.Success(null))
    val latestPhonesState = _latestPhonesState.asStateFlow()

    init {
//        getLatestPhones()
    }

//    fun getLatestPhones() {
//        repository.getLatestPhones()
//            .onEach { _latestPhonesState.value = it }
//            .launchIn(viewModelScope)
//    }
}