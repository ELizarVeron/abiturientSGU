package com.android.abiturientsgu.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.abiturientsgu.domain.models.Profile
import com.android.abiturientsgu.domain.models.User
import com.android.abiturientsgu.domain.repository.AuthRepo
import kotlinx.coroutines.launch


class RegistrationViewModel(private var repository: AuthRepo) : ViewModel() {

    private var _registerResult = MutableLiveData<Result<String>>()
    var registerResult: LiveData<Result<String>> = _registerResult


    fun createNewUser(
        login: String,
        lastname: String?,
        name: String?,
        patronymic: String?,
        school: String?,
        classs: String?,
        tel: String?,
        themes: List<String>?,
        password: String
    ) {
        val pass = User.Password.createIfValid(password)
        if (pass == null) _registerResult.value = Result.failure(Throwable("Невалидынй пароль"))
        else {
            val nProfile = Profile(
                login, lastname, name, patronymic, school, classs, tel, themes
            )
            viewModelScope.launch {

                repository.registration(nProfile, pass).collect {
                    if (it == "success") _registerResult.value = Result.success("success")
                    else _registerResult.value = Result.failure(Throwable(it))
                }

            }
        }
    }
}