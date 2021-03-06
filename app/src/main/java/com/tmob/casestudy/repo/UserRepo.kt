package com.tmob.casestudy.repo

import com.tmob.casestudy.network.UserService
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class UserRepo @Inject constructor(
    private val widgetService: UserService
) {

    suspend fun getUserData() = coroutineScope {
        async {
            return@async widgetService.getUserData()
        }
    }.await()


    suspend fun getUserDetail(url: String) = coroutineScope {
        async {
            return@async widgetService.getUserDetail(url)
        }
    }.await()


}