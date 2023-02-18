package com.android.abiturientsgu.data.specialty

import com.android.abiturientsgu.data.specialty.locale.SpecialtiesLocalDataSource
import com.android.abiturientsgu.data.specialty.remote.SpecialtiesRemoteDataSource
import com.android.abiturientsgu.domain.models.Specialty
import com.android.abiturientsgu.domain.repository.SpecialtiesRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import toSpecialityEntity
import toSpecialty

class SpecialtiesRepoImpl(
    private val remoteDataSource: SpecialtiesRemoteDataSource,
    private val localDataSource: SpecialtiesLocalDataSource
) : SpecialtiesRepo {
    override suspend fun fetchSpecialties(): Flow<List<Specialty>> {
        CoroutineScope(Dispatchers.IO).launch {
            getSpecialtiesFromApiAndStore()
        }
        return  observeSpecialtiesFromDB()
    }

    private suspend fun getSpecialtiesFromApiAndStore() {
        val specialties = remoteDataSource.getSpecialities()
        if (specialties.isSuccess) {
            if(specialties.getOrNull() !=null){
                localDataSource.deleteAll()
                localDataSource.insertAll(specialties.getOrNull()!!.map{ it.toSpecialityEntity() })
            }
        }
    }

    //загружаем из базы
    private fun observeSpecialtiesFromDB(): Flow<List<Specialty>> {
        return localDataSource.getAll().map { it.map { it1->it1.toSpecialty() } }
    }


    ///////
    override suspend fun fetchSpecialty(id: Int): Flow<Specialty> {
        CoroutineScope(Dispatchers.IO).launch {
            getSpecialtyFromApiAndStore(id)
        }
        return observeSpecialtyFromDB(id)
    }

    private suspend fun getSpecialtyFromApiAndStore(id: Int) {
        val specialty = remoteDataSource.getSpeciality(id)
        if (specialty.isSuccess) {
            if (specialty.getOrNull() != null) {
                //  localDataSource.deleteAll()
                localDataSource.insert(specialty.getOrNull()!!.toSpecialityEntity())
            }
        }
    }

    //загружаем из базы
    private fun observeSpecialtyFromDB(id: Int): Flow<Specialty> {
        return localDataSource.getById(id).map { it.toSpecialty() }
    }
}