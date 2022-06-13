package com.tmob.casestudy.usecase

import com.tmob.casestudy.model.UserListResponse
import com.tmob.casestudy.model.UserDetailResponse
import com.tmob.casestudy.repo.UserRepo
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject



class UserUseCase @Inject constructor(private val repository:UserRepo) {


    suspend fun getMenuData() :UserListResponse= coroutineScope {
        async{
            return@async repository.getUserData()
        }
    }.await()

    suspend fun getUserDetail(login: String):UserDetailResponse= coroutineScope {
        async{
            return@async repository.getUserDetail(login)
        }
    }.await()

}


