package com.android.abiturientsgu.data.profile


import android.util.Log
import com.android.abiturientsgu.data.profile.locale.ProfileLocalDataSource
import com.android.abiturientsgu.data.profile.remote.ProfileRemoteDataSource
import com.android.abiturientsgu.domain.models.Profile
import com.android.abiturientsgu.domain.repository.ProfileRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
        TODO("Not yet implemented")
    }

    override suspend fun updatePass(
        login: String,
        oldPass: String,
        newPass: String
    ): Result<String> {
        TODO("Not yet implemented")
    }

    override suspend fun updateInterests(login: String, themes: String): Result<String> {
        TODO("Not yet implemented")
    }

    private suspend fun getProfileFromApiAndStore(login: String) {
        val profile = remoteDataSource.getProfile(login)
        if (profile.isSuccess) {
            if (profile.getOrNull() != null) {
                withContext(Dispatchers.IO) {
                    Log.d(
                        "OLOLO",
                        " localDataSource.insertProfileInfo(profile.getOrNull()!!.toProfileEntity())             "
                    )
                    localDataSource.insertProfileInfo(profile.getOrNull()!!.toProfileEntity())
                }
            }
        }
    }
    //загружаем из базы
    private fun observeProfileFromDB(login: String): Flow<Profile> {
        return localDataSource.getProfileInfo(login).map { it.toProfile() }
    }

}
