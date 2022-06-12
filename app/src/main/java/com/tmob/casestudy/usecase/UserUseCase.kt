package com.tmob.casestudy.usecase

import com.tmob.casestudy.model.UserResponse
import com.tmob.casestudy.repo.UserRepo
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject


class UserUseCase @Inject constructor(private val repository:UserRepo) {


    suspend fun getMenuData() :UserResponse= coroutineScope {
        async{
            return@async repository.getUserData()
        }
    }.await()



/*
    suspend fun getMenu(): MenuModel? {
        val menuResponse: MenuModel?
        try {
            menuResponse = repository.getMenuData()
        } catch (e: Exception) {
            throw e
        }
        return menuResponse
    }
*/

/*    suspend fun getProducts(url:String): ProductModelArray? {
        val productListResponse: ProductModelArray?
        try {
            productListResponse = repository.getProductData(url)
        } catch (e: Exception) {
            throw e
        }
        return productListResponse
    }*/

}