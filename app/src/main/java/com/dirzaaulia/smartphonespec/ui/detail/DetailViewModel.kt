package com.dirzaaulia.smartphonespec.ui.detail

import androidx.lifecycle.ViewModel
import com.dirzaaulia.smartphonespec.domain.model.PhoneDetail
import com.dirzaaulia.smartphonespec.domain.response.PhoneDetailResponse
import com.dirzaaulia.smartphonespec.utils.ResponseResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
): ViewModel() {

    private val _phoneDetailState: MutableStateFlow<ResponseResult<PhoneDetailResponse?>> =
        MutableStateFlow(ResponseResult.Success(null))
    val phoneDetailState = _phoneDetailState.asStateFlow()

    private val _phoneDetail: MutableStateFlow<PhoneDetail?> =
        MutableStateFlow(null)
    val phoneDetail = _phoneDetail.asStateFlow()

//    fun getPhoneDetail(slug: String) {
//        repository.getPhoneDetail(slug)
//            .onEach {
//                _phoneDetailState.value = it
//                it.success { response -> _phoneDetail.value =  response.data }
//            }
//            .launchIn(viewModelScope)
//    }
}