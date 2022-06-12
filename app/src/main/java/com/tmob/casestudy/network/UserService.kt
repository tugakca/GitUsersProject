package com.tmob.casestudy.network

import com.tmob.casestudy.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import retrofit2.http.Url


interface UserService {


    @GET("/users")
    suspend fun getUserData(
    ):UserResponse

/*    @GET
    suspend fun getProductListData(@Url url:String?):ProductModelArray*/

}
