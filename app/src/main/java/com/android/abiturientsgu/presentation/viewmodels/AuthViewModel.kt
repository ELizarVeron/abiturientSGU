package com.android.abiturientsgu.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.abiturientsgu.domain.models.User
import com.android.abiturientsgu.domain.repository.AuthRepo
import kotlinx.coroutines.launch

class AuthViewModel(private var repository: AuthRepo) : ViewModel() {
    init {
        Log.d("OLOLO", "AuthViewModel created")
    }


    var currentLogin = repository.getCurrentUser()

    private var _authResult = MutableLiveData<Result<String>>()
    var authResult: LiveData<Result<String>> = _authResult





    fun auth(login: String, password: String) {
        val pass = User.Password.createTESTFUN(password)
        viewModelScope.launch {
            repository.auth(login, pass).collect {
                if (it == "success") _authResult.value = Result.success("success")
                else _authResult.value = Result.failure(Throwable(it))
            }
        }
        /* if (pass == null) _authResult.value = Result.failure(Throwable("Невалидынй пароль") )
         else {

         }*/
    }


    fun logout() {
        repository.logout()
    }


}
