package com.android.abiturientsgu.presentation.viewmodels

import androidx.lifecycle.*
import com.android.abiturientsgu.domain.models.Profile
import com.android.abiturientsgu.domain.models.User
import com.android.abiturientsgu.domain.repository.ProfileRepo
import com.android.abiturientsgu.utils.Interests
import kotlinx.coroutines.launch

class ProfileViewModel(private var repository: ProfileRepo) : ViewModel() {

    private var _resultUpdateProfile = MutableLiveData<String>()
    var resultUpdateProfile = _resultUpdateProfile as LiveData<String>

    private var _resultChangePass = MutableLiveData<String>()
    var resultChangePass = _resultChangePass as LiveData<String>

    var profileResult = liveData<Profile> {}

    fun getProfile(login: String) {
        viewModelScope.launch {
            profileResult = repository.fetchProfile(login).asLiveData()
        }
    }

    fun updateInterests(login: String, inters: List<Interests>) {
        viewModelScope.launch {
            val res =
                repository.updateInterests(login, inters.joinToString(separator = ",") { it.value })
            _resultUpdateProfile.value = res.toString()
            res.onSuccess {
                getProfile(login)
            }
        }
    }

    fun updateProfile(profile: Profile) {
        viewModelScope.launch {
            val res = repository.updateProfile(profile)
            _resultUpdateProfile.value = res.toString()
            res.onSuccess {
                getProfile(profile.login)
            }
        }
    }

    fun updatePass(login: String, oldPassword: User.Password, newPassword: User.Password) {
        viewModelScope.launch {
            val res = repository.updatePass(login, oldPassword, newPassword)

            res.onSuccess {
                _resultChangePass.value = it
            }

            res.onFailure {
                _resultChangePass.value = it.message
            }

        }
    }
}
