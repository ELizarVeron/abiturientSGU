package com.android.abiturientsgu.domain.models

import java.io.Serializable

data class Specialty(
    val id: Int,
    val specialty: String,
    val code: String,
    val form: String,
    val years: String,
    val description: String,
    val subjects: List<String>,
    val exams: List<String>,
    val spheres: List<String>,
    val professions: List<String>,

    ) : Serializable