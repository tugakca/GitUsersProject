package com.tmob.casestudy.usecase

import com.tmob.casestudy.model.Repository
import com.tmob.casestudy.model.UserListResponse
import com.tmob.casestudy.model.UserResponse
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


suspend fun getCommonFollowData(url:String):UserListResponse= coroutineScope {
        async{
            return@async repository.getFollowInfo(url)
        }
    }.await()

    suspend fun getFileInfo(url:String):Repository= coroutineScope {
        async{
            return@async repository.getFileInfo(url)
        }
    }.await()

    suspend fun getUserDetail(login: String):UserResponse= coroutineScope {
        async{
            return@async repository.getUserDetail(login)
        }
    }.await()

}


