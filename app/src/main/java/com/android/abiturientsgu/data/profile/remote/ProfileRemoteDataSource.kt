package com.android.abiturientsgu.data.profile.remote

import com.android.abiturientsgu.data.core.network.RetrofitServices
import com.android.abiturientsgu.data.profile.toProfile
import com.android.abiturientsgu.domain.models.Profile
import com.android.abiturientsgu.domain.models.User

interface ProfileRemoteDataSource {
    suspend fun getProfile(login: String): Result<Profile>
    suspend fun updateInterests(login: String, interests: String): Result<String>
    suspend fun updateProfile(profile: Profile): Result<String>
    suspend fun updatePass(
        login: String,
        oldPassword: User.Password,
        newPassword: User.Password
    ): Result<String>
}

class ProfileRemoteDataSourceImpl(private val rService: RetrofitServices) :
    ProfileRemoteDataSource {

    override suspend fun getProfile(login: String): Result<Profile> =
        try {
            Result.success(rService.GetProfileInfo(login).toProfile())
        } catch (e: Exception) {
            Result.failure(e)
        }

    override suspend fun updateInterests(login: String, interests: String): Result<String> =
        try {
            Result.success(rService.UpdateInterests(login, interests).response)
        } catch (e: Exception) {
            Result.failure(e)
        }

    override suspend fun updateProfile(profile: Profile): Result<String> =
        try {
            Result.success(
                rService.UpdateProfile(
                    profile.login,
                    profile.lastname ?: "",
                    profile.name ?: "",
                    profile.patronymic ?: "",
                    profile.school ?: "",
                    profile.classs ?: "",
                    profile.tel ?: "",
                ).response
            )
        } catch (e: Exception) {
            Result.failure(e)
        }


    override suspend fun updatePass(
        login: String,
        oldPassword: User.Password,
        newPassword: User.Password
    ): Result<String> =
        try {
            Result.success(
                rService.UpdatePassword(
                    login,
                    oldPassword.value,
                    newPassword.value
                ).response
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
}


