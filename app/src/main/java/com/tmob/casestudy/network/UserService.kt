package com.tmob.casestudy.network

import com.tmob.casestudy.model.UserListResponse
import com.tmob.casestudy.model.UserDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path


interface UserService {

    @GET("/users")
    suspend fun getUserData(
    ): UserListResponse

    @GET("/users/{login}")
    suspend fun getUserDetail(@Path("login") login: String): UserDetailResponse
}
