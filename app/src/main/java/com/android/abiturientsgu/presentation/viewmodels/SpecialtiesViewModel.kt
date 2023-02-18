package com.android.abiturientsgu.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.android.abiturientsgu.domain.models.Specialty
import com.android.abiturientsgu.domain.repository.SpecialtiesRepo
import kotlinx.coroutines.launch

class SpecialtiesViewModel(private var repository: SpecialtiesRepo) : ViewModel() {

    var specialties = liveData<List<Specialty>> {}
    var specialty = liveData<Specialty> {}

    init {
        getSpecialties()
    }

    private fun getSpecialties() {
        viewModelScope.launch {
            specialties = repository.fetchSpecialties().asLiveData()

        }
    }

    private fun getSpecialty(id: Int) {
        viewModelScope.launch {
            specialty = repository.fetchSpecialty(id).asLiveData()

        }
    }

    fun getSpeciality(id: Int): Specialty? =
        specialties.value?.first {
            it.id == id
        }
}
