package com.android.abiturientsgu.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.abiturientsgu.domain.repository.AuthRepo


class RegistrationViewModel(private var repository: AuthRepo) : ViewModel() {

    init {
        Log.d("Test", "RegistrationViewModelCreated")
    }

    var inputLogin = MutableLiveData<String>()

    var inputPassword = MutableLiveData<String>()

    var confirmPassword = MutableLiveData<String>()

    private fun register(){

    }
}