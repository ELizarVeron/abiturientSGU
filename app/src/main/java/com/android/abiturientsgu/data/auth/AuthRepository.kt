package com.android.abiturientsgu.data.auth

import com.android.abiturientsgu.domain.models.Profile
import com.android.abiturientsgu.domain.models.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun auth(login: String, password: User.Password): Flow<String>

    suspend fun registration(newProfile: Profile, password: User.Password): Flow<String>
}
