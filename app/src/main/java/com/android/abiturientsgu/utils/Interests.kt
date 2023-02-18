package com.android.abiturientsgu.utils

enum class Interests(val value: String) {
    IT("ИТ"),
    PROGRAMMING("Программирование"),
    MATH("Математика"),
    IB("ИБ"),
    PEDAGOGY("Педагогика"),
    LING("Лингвистика"),
    JURIS("Юриспруденция"),
    PHILO("Философия"),
    MED("Медицина"),
    ECONOMIC("Экономика"),
    DESIGN("Дизайн")

}

inline fun <reified T : Enum<T>> enumContains(name: String): Boolean {
    return enumValues<T>().any { it.name == name }
}


inline fun <reified T : Enum<T>> enumToBooleanArray(list: List<Interests>): BooleanArray {
    return Interests.values().map {
        list.contains(it)
    }.toBooleanArray()
}

fun fromBooleanArrayToInterestsArray(arr: BooleanArray): List<Interests> =
    Interests.values().filterIndexed { index, _ -> arr[index] }

