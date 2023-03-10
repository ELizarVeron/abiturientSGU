package com.android.abiturientsgu.data.profile


import com.android.abiturientsgu.data.profile.locale.ProfileLocalDataSource
import com.android.abiturientsgu.data.profile.remote.ProfileRemoteDataSource
import com.android.abiturientsgu.domain.models.Profile
import com.android.abiturientsgu.domain.models.User
import com.android.abiturientsgu.domain.repository.ProfileRepo
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class ProfileRepoImpl(
    private val remoteDataSource: ProfileRemoteDataSource,
    private val localDataSource: ProfileLocalDataSource
) : ProfileRepo {

    override suspend fun fetchProfile(login: String): Flow<Profile> {
        CoroutineScope(Dispatchers.IO).launch {
            getProfileFromApiAndStore(login)
        }
        return observeProfileFromDB(login)
    }

    override suspend fun updateProfile(profile: Profile): Result<String> {
        val response = CoroutineScope(Dispatchers.IO).async {
            remoteDataSource.updateProfile(profile)
        }
        return response.await()
    }

    override suspend fun updatePass(
        login: String,
        oldPass: User.Password,
        newPass: User.Password
    ): Result<String> {
        val response = CoroutineScope(Dispatchers.IO).async {
            remoteDataSource.updatePass(login, oldPass, newPass)
        }
        return response.await()
    }

    override suspend fun updateInterests(login: String, themes: String): Result<String> {
        val response = CoroutineScope(Dispatchers.IO).async {
            remoteDataSource.updateInterests(login, themes)
        }
        return response.await()
    }

    private suspend fun getProfileFromApiAndStore(login: String) {
        val profile = remoteDataSource.getProfile(login)
        if (profile.isSuccess) {
            if (profile.getOrNull() != null) {
                withContext(Dispatchers.IO) {

                localDataSource.insertProfileInfo(profile.getOrNull()!!.toProfileEntity())
                }
            }
        }
    }
    //?????????????????? ???? ????????
    private fun observeProfileFromDB(login: String): Flow<Profile> {

        return localDataSource.getProfileInfo(login).map { it.toProfile() }
    }

}
