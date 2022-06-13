package com.tmob.casestudy.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tmob.casestudy.model.UserListResponse
import com.tmob.casestudy.model.UserDetailResponse
import com.tmob.casestudy.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val menuUseCase: UserUseCase) : ViewModel() {

    val userListResponse: MutableLiveData<UserListResponse> by lazy { MutableLiveData<UserListResponse>() }
    val userDetailResponse: MutableLiveData<UserDetailResponse> by lazy { MutableLiveData<UserDetailResponse>() }
    val loading: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val error: MutableLiveData<Exception> by lazy { MutableLiveData<Exception>() }

    fun getUserData() {
        loading.value = true
        viewModelScope.launch {
            try {
                userListResponse.value = menuUseCase.getMenuData()
                loading.value = false
            } catch (exception: Exception) {
                error.value = exception
            }
        }
    }

    fun getUserDetail(login: String) {
        loading.value = true
        viewModelScope.launch {
            try {
                userDetailResponse.value = menuUseCase.getUserDetail(login)
                loading.value = false
            } catch (exception: Exception) {
                error.value = exception
                loading.value = false
            }
        }
    }

}