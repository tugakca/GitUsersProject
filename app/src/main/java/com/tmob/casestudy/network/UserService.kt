package com.tmob.casestudy.network

import com.tmob.casestudy.model.Repository
import com.tmob.casestudy.model.UserListResponse
import com.tmob.casestudy.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url


interface UserService {


    @GET("/users")
    suspend fun getUserData(
    ):UserListResponse

    @GET("/users/{login}")
    suspend fun getUserDetail(@Path("login") login:String):UserResponse

    @GET
    suspend fun getFollowInfo(@Url url:String?):UserListResponse

    @GET
    suspend fun getFileInfo(@Url url:String?):Repository

}
