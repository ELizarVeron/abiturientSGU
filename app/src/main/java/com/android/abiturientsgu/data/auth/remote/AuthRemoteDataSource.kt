package com.android.abiturientsgu.data.auth.remote

import com.android.abiturientsgu.data.core.network.RetrofitServices
import com.android.abiturientsgu.domain.models.Profile
import com.android.abiturientsgu.domain.models.User


interface AuthRemoteDataSource {

    suspend fun createProfile(
        profile: Profile,
        password: User.Password,
    ): Result<String>

    suspend fun logIn(
        login: String,
        password: User.Password,
    ): Result<String>

}

class AuthRemoteDataSourceImpl(private val rService: RetrofitServices) : AuthRemoteDataSource {
    override suspend fun createProfile(profile: Profile, password: User.Password): Result<String> =
        try {
            Result.success(
                rService.Registration(
                    profile.login,
                    password.value,
                    profile.lastname ?: "",
                    profile.name ?: "",
                    profile.patronymic ?: "",
                    profile.school ?: "",
                    profile.classs ?: "",
                    profile.tel ?: "",
                    profile.themes?.joinToString(separator = ",") ?: ""
                )
            )
        } catch (e: Exception) {
            Result.failure(e)
        }


    override suspend fun logIn(login: String, password: User.Password): Result<String> =
        try {
            Result.success(rService.Login(login, password.value))
        } catch (e: Exception) {
            Result.failure(e)
        }


}
