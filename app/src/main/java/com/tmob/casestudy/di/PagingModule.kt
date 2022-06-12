package com.tmob.casestudy.di

import com.tmob.casestudy.network.UserService
import com.tmob.casestudy.paging.UserPagingResource
import com.tmob.casestudy.repo.UserRepo
import com.tmob.casestudy.usecase.UserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PagingModule {

    @Provides
    @Singleton
    fun providePaging(
          useCase: UserUseCase
    ): UserPagingResource {
        return UserPagingResource(useCase)
    }

}