package com.tmob.casestudy.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tmob.casestudy.model.Repository
import com.tmob.casestudy.model.UserListResponse
import com.tmob.casestudy.model.UserResponse
import com.tmob.casestudy.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val menuUseCase: UserUseCase) : ViewModel() {

    val userListResponse: MutableLiveData<UserListResponse> by lazy { MutableLiveData<UserListResponse>() }
    val followersResponse: MutableLiveData<UserListResponse> by lazy { MutableLiveData<UserListResponse>() }
    val followingResponse: MutableLiveData<UserListResponse> by lazy { MutableLiveData<UserListResponse>() }
    val userDetailResponse: MutableLiveData<UserResponse> by lazy { MutableLiveData<UserResponse>() }

    val repoResponse: MutableLiveData<Repository> by lazy { MutableLiveData<Repository>() }
    val orgResponse: MutableLiveData<Repository> by lazy { MutableLiveData<Repository>() }
    val starredResponse: MutableLiveData<Repository> by lazy { MutableLiveData<Repository>() }

    val loading: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val error: MutableLiveData<Exception> by lazy { MutableLiveData<Exception>() }

    fun getUserData() {
        //loading.value = true
        viewModelScope.launch {
            try {
                userListResponse.value = menuUseCase.getMenuData()
                loading.value = false
            } catch (exception: Exception) {
                error.value = exception
            }
        }
    }

    fun getFollowersData(url: String) {
        viewModelScope.launch {
            try {
                followersResponse.value = menuUseCase.getCommonFollowData(url)
                loading.value = false
            } catch (exception: Exception) {
                error.value = exception
                loading.value = false
            }
        }
    }

    fun getFollowingData(url: String) {
        viewModelScope.launch {
            try {
                followingResponse.value = menuUseCase.getCommonFollowData(url)
                loading.value = false
            } catch (exception: Exception) {
                error.value = exception
                loading.value = false
            }
        }
    }

    fun gettRepoData(url: String) {
        viewModelScope.launch {
            try {
                repoResponse.value = menuUseCase.getFileInfo(url)
                loading.value = false
            } catch (exception: Exception) {
                error.value = exception
                loading.value = false
            }
        }
    }

    fun getStarredData(url: String) {
        viewModelScope.launch {
            try {
                starredResponse.value = menuUseCase.getFileInfo(url)
                loading.value = false
            } catch (exception: Exception) {
                error.value = exception
                loading.value = false
            }
        }
    }

    fun getOrganisationData(url: String) {
        viewModelScope.launch {
            try {
                orgResponse.value = menuUseCase.getFileInfo(url)
                loading.value = false
            } catch (exception: Exception) {
                error.value = exception
                loading.value = false
            }
        }
    }

    fun getUserDetail(login: String) {
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