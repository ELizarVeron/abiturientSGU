package com.android.abiturientsgu.domain.models



data class User(
    val login: String,
    val password: Password,
) {

    @JvmInline
    value class Password private constructor(val value: String) {

        companion object {

            private const val MINIMAL_PASSWORD_LENGTH = 6

            fun createIfValid(value: String?): Password? =
                if ((value != null)
                    && value.isNotBlank()
                    && (value.length >= MINIMAL_PASSWORD_LENGTH)
                ) {
                    Password(value)
                } else {
                    null
                }

            fun createTESTFUN(value: String): Password = Password(value)
        }
    }
}