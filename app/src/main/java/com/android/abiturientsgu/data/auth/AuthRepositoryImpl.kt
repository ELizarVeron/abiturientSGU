package com.android.abiturientsgu.data.auth

import com.android.abiturientsgu.data.auth.local.AuthLocalDataSource
import com.android.abiturientsgu.data.auth.remote.AuthRemoteDataSource
import com.android.abiturientsgu.domain.models.Profile
import com.android.abiturientsgu.domain.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf


class AuthRepositoryImpl  (
    private val localDataSource: AuthLocalDataSource,
    private val remoteDataSource: AuthRemoteDataSource,
) : AuthRepository {

    override suspend fun auth(login: String, password: User.Password): Flow<String> {
        val res = remoteDataSource.logIn(login, password)
        if (res.isSuccess) {
            localDataSource.saveCurUSer(login)
        }
        return flowOf(res.getOrDefault(""))
    }

    override suspend fun registration(newProfile: Profile, password: User.Password): Flow<String> {
        val res = remoteDataSource.createProfile(newProfile, password)
        return flowOf(res.getOrDefault(""))
    }


}
