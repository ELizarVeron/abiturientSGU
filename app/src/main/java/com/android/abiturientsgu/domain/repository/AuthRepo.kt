package com.android.abiturientsgu.domain.repository

import com.android.abiturientsgu.domain.models.Profile
import com.android.abiturientsgu.domain.models.User
import kotlinx.coroutines.flow.Flow

interface AuthRepo {
    suspend fun auth(login: String, password: User.Password): Flow<String>

    suspend fun registration(newProfile: Profile, password: User.Password): Flow<String>

    fun getCurrentUser(): String?

    fun logout()
}
