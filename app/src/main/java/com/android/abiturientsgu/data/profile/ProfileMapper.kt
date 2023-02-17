package com.android.abiturientsgu.data.profile

import com.android.abiturientsgu.data.profile.locale.ProfileEntity
import com.android.abiturientsgu.data.profile.remote.ProfileDto
import com.android.abiturientsgu.domain.models.Profile

fun ProfileDto.toProfile(): Profile {

    val themesList  = themes.split(",")

    return Profile(
        login,  lastname, name, patronymic, school, classs, tel, themesList
    )
}

fun ProfileEntity.toProfile(): Profile {

    val themesList  = themes.split(",")

    return Profile(
        login,  lastname, name, patronymic, school, classs, tel, themesList
    )
}

fun Profile.toProfileEntity(): ProfileEntity {

    var themesList = themes?.joinToString(separator = ",").toString()


    return ProfileEntity(
        login,
        lastname ?: "",
        name ?: "",
        patronymic ?: "",
        school ?: "",
        classs ?: "",
        tel ?: "",
        themesList
    )
}