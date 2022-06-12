package com.tmob.casestudy.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tmob.casestudy.model.UserResponse
import com.tmob.casestudy.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val menuUseCase: UserUseCase) : ViewModel() {

    val userResponse: MutableLiveData<UserResponse> by lazy { MutableLiveData<UserResponse>() }

    val loading: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val error: MutableLiveData<Exception> by lazy { MutableLiveData<Exception>() }

    fun getUserData() {
        //loading.value = true
        viewModelScope.launch {
            try {
                userResponse.value = menuUseCase.getMenuData()
                // loading.value = false
            } catch (exception: Exception) {
                error.value = exception
            }
        }
    }

/*    fun getProduct(url: String) {
       // loading.value = true
        viewModelScope.launch {
            try {
                productResponse.value = menuUseCase.getProducts(url)
                //loading.value = false
            } catch (exception: Exception) {
                error.value = exception
               // loading.value = false
            }
        }
    }


    fun getProductList(url: String, ) {
        //loading.value = true
        viewModelScope.launch {
            try {
                productListingResponse.value = menuUseCase.getProducts(url)
                //loading.value = false
            } catch (exception: Exception) {
                error.value = exception
               // loading.value = false
            }
        }
    }*/

}