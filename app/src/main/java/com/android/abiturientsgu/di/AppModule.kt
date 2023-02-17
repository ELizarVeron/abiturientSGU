package com.android.abiturientsgu.di

import androidx.room.Room
import com.android.abiturientsgu.data.auth.AuthRepositoryImpl
import com.android.abiturientsgu.data.auth.local.AuthLocalDataSource
import com.android.abiturientsgu.data.auth.remote.AuthRemoteDataSource
import com.android.abiturientsgu.data.auth.remote.AuthRemoteDataSourceImpl
import com.android.abiturientsgu.data.core.db.AbiturienDb
import com.android.abiturientsgu.data.core.network.RetrofitServices
import com.android.abiturientsgu.data.events.EventsRepoImpl
import com.android.abiturientsgu.data.events.locale.EventsLocalDataSource
import com.android.abiturientsgu.data.events.locale.EventsLocalDataSourceImpl
import com.android.abiturientsgu.data.events.remote.EventsRemoteDataSource
import com.android.abiturientsgu.data.events.remote.EventsRemoteDataSourceImpl
import com.android.abiturientsgu.data.profile.ProfileRepoImpl
import com.android.abiturientsgu.data.profile.locale.ProfileLocalDataSource
import com.android.abiturientsgu.data.profile.locale.ProfileLocalDataSourceImpl
import com.android.abiturientsgu.data.profile.remote.ProfileRemoteDataSource
import com.android.abiturientsgu.data.profile.remote.ProfileRemoteDataSourceImpl
import com.android.abiturientsgu.data.specialty.SpecialtiesRepoImpl
import com.android.abiturientsgu.data.specialty.locale.SpecialtiesLocalDataSource
import com.android.abiturientsgu.data.specialty.locale.SpecialtiesLocalDataSourceImpl
import com.android.abiturientsgu.data.specialty.remote.SpecialtiesRemoteDataSource
import com.android.abiturientsgu.data.specialty.remote.SpecialtiesRemoteDataSourceImpl
import com.android.abiturientsgu.domain.repository.AuthRepo
import com.android.abiturientsgu.domain.repository.EventsRepo
import com.android.abiturientsgu.domain.repository.ProfileRepo
import com.android.abiturientsgu.domain.repository.SpecialtiesRepo
import com.android.abiturientsgu.presentation.viewmodels.AuthViewModel
import com.android.abiturientsgu.presentation.viewmodels.EventsViewModel
import com.android.abiturientsgu.presentation.viewmodels.ProfileViewModel
import com.android.abiturientsgu.presentation.viewmodels.SpecialtiesViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

private const val TIMEOUT: Long = 30
private const val BASE_URL = "http://atlas-prof.syktsu.ru/webservice.asmx/"
val AppModule = module {

    viewModel {
        ProfileViewModel(repository = get())
    }

    viewModel {
        EventsViewModel(repository = get())
    }

    viewModel {
        SpecialtiesViewModel(repository = get())
    }

    viewModel {
        AuthViewModel(repository = get())
    }
////////////////////////////////////////////
    single<ProfileRepo> {
        ProfileRepoImpl(
            remoteDataSource = get(),
            localDataSource = get()
        )
    }

    single<ProfileRemoteDataSource> {
        ProfileRemoteDataSourceImpl(
            rService = get()
        )
    }

    single<ProfileLocalDataSource> {
        ProfileLocalDataSourceImpl(profileDao = get())
    }
////////////////////////////////////////////
    single<EventsRepo> {
        EventsRepoImpl(
            remoteDataSource = get(),
            localDataSource = get()
        )
    }

    single<EventsRemoteDataSource> {
        EventsRemoteDataSourceImpl(
            rService = get()
        )
    }

    single<EventsLocalDataSource> {
        EventsLocalDataSourceImpl(eventDao = get())
    }

    ////////////////////////////////////////////
    single<SpecialtiesRepo> {
        SpecialtiesRepoImpl(
            remoteDataSource = get(),
            localDataSource = get()
        )
    }

    single<SpecialtiesRemoteDataSource> {
        SpecialtiesRemoteDataSourceImpl(
            rService = get()
        )
    }

    single<SpecialtiesLocalDataSource> {
        SpecialtiesLocalDataSourceImpl(specialtyDao = get())
    }


    ////////////////

    single<AuthRepo> {
        AuthRepositoryImpl(
            remoteDataSource = get(),
            localDataSource = get()
        )
    }

    single<AuthRemoteDataSource> {
        AuthRemoteDataSourceImpl(
            rService = get()
        )
    }

    single {
        AuthLocalDataSource(context = androidContext())
    }

    single {
        val database = get<AbiturienDb>()
        database.eventDao()
    }

    single {
        val database = get<AbiturienDb>()
        database.profileDao()
    }

    single {
        val database = get<AbiturienDb>()
        database.specialtyDao()
    }


    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitServices::class.java)

    }
    single {
        OkHttpClient.Builder()
            .addInterceptor(interceptor = get() )
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .build()

    }
    single<okhttp3.Interceptor> {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    }

    single {
        Room.databaseBuilder(
            androidContext(),
            AbiturienDb::class.java,
            "abiturient_sgu__database"
        ).apply {
            setQueryCallback({ sqlQuery, bindArgs ->
                println("SQL Query: $sqlQuery SQL Args: $bindArgs")
            }, Executors.newSingleThreadExecutor())
        }.build()
    }


}
