package com.tmob.casestudy.di
import com.tmob.casestudy.network.UserService
import com.tmob.casestudy.repo.UserRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    @Singleton
    fun provideUserRepo(
        userService: UserService,
        ): UserRepo {
        return UserRepo(userService)
    }

}







