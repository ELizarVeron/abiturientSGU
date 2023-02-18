package com.android.abiturientsgu.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.android.abiturientsgu.domain.repository.AuthRepo

class LoginViewModel(private var repository: AuthRepo) : ViewModel() {

    init {
        Log.d("Test", "LoginViewModelCreated")
    }

    var inputLogin = MutableLiveData<String>()

    var inputPassword = MutableLiveData<String>()

    private fun login() {

    }

    private fun register(){

    }

}