package com.android.abiturientsgu.data.core.network

import com.android.abiturientsgu.data.events.remote.EventDto
import com.android.abiturientsgu.data.profile.remote.ProfileDto
import com.android.abiturientsgu.data.specialty.remote.SpecialtyDto
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServices {

    //@Headers("Content-Type:application/json")
    @GET("Registration")
    suspend fun Registration(
        @Query("login") login: String,
        @Query("pass") pass: String,
        @Query("fam") fam: String,
        @Query("im") name: String,
        @Query("otch") patronymic: String,
        @Query("school") school: String,
        @Query("classs") classs: String,
        @Query("tel") tel: String,
        @Query("themes") themes: String,
    ): ResponseDto


    @GET("Login")
    suspend fun Login(
        @Query("login") login: String,
        @Query("pass") pass: String,
    ): ResponseDto

    @GET("CancelRecord")
    suspend fun CancelRecord(
        @Query("login") login: String,
        @Query("eventt") event: Int,
    ): ResponseDto

    @GET("GetAbiturThemes")
    suspend fun GetAbiturThemes(
        @Query("login") login: String,
    ): String

    @GET("GetEvents")
    suspend fun GetEvents(
    ): List<EventDto>

    @GET("GetEventsByDate")
    suspend fun GetEventsByDate(
        @Query("datee") date: String
    ): List<EventDto>

    @GET("GetEventsByThemes")
    suspend fun GetEventsByThemes(
        @Query("login") login: String,
        @Query("themes") themes: String,
        @Query("offset") offset: String
    ): List<EventDto>

    @GET("GetProfileInfo")
    suspend fun GetProfileInfo(
        @Query("login") login: String,
    ): ProfileDto

    @GET("GetSpecialties")
    suspend fun GetSpecialties(
    ): List<SpecialtyDto>



    @GET("GetSpecialtyInfo")
    suspend fun GetSpecialtyInfo(
        @Query("id") id: Int,
    ): SpecialtyDto


    @GET("PostRecord")
    suspend fun PostRecord(
        @Query("login") login: String,
        @Query("eventt") eventId: Int,
    ): ResponseDto


    @GET("PostReview")  //тут можно оставлять отзыв только к етм мероприятиям,на которые чел был записан
    suspend fun PostReview(
        @Query("login") login: String,
        @Query("eventt") eventId: Int,
        @Query("text") text: String,
    ): ResponseDto

/////

    @GET("UpdateProfile")
    suspend fun UpdateProfile(
        @Query("login") login: String,
        @Query("fam") fam: String,
        @Query("im") name: String,
        @Query("otch") patronymic: String,
        @Query("school") school: String,
        @Query("classs") classs: String,
        @Query("tel") tel: String,
    ): ResponseDto


    @GET("UpdateInterests")
    suspend fun UpdateInterests(
        @Query("login") login: String,
        @Query("themes") themes: String,
    ): ResponseDto


    @GET("UpdatePassword")
    suspend fun UpdatePassword(
        @Query("login") login: String,
        @Query("oldpass") oldPass: String,
        @Query("newpass") newPass: String,
    ): ResponseDto






}