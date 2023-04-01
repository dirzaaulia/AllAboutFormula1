package com.dirzaaulia.smartphonespec.ui.detail

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dirzaaulia.smartphonespec.domain.data.model.PhoneDetail
import com.dirzaaulia.smartphonespec.domain.data.response.PhoneDetailResponse
import com.dirzaaulia.smartphonespec.repository.Repository
import com.dirzaaulia.smartphonespec.utils.ResponseResult
import com.dirzaaulia.smartphonespec.utils.success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    private val _phoneDetailState: MutableStateFlow<ResponseResult<PhoneDetailResponse?>> =
        MutableStateFlow(ResponseResult.Success(null))
    val phoneDetailState = _phoneDetailState.asStateFlow()

    private val _phoneDetail: MutableStateFlow<PhoneDetail?> =
        MutableStateFlow(null)
    val phoneDetail = _phoneDetail.asStateFlow()

    fun getPhoneDetail(slug: String) {
        repository.getPhoneDetail(slug)
            .onEach {
                _phoneDetailState.value = it
                it.success { response -> _phoneDetail.value =  response.data }
            }
            .launchIn(viewModelScope)
    }
}