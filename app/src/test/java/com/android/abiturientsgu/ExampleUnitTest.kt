package com.android.abiturientsgu

import com.android.abiturientsgu.data.profile.locale.ProfileEntity
import com.android.abiturientsgu.data.profile.remote.ProfileDto
import com.android.abiturientsgu.data.profile.toProfile
import com.android.abiturientsgu.data.profile.toProfileEntity
import com.android.abiturientsgu.domain.models.Profile
import com.android.abiturientsgu.utils.Interests
import com.android.abiturientsgu.utils.enumContains
import com.android.abiturientsgu.utils.enumToBooleanArray
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun ProfileDto_toProfile_isCorrect() {
        val pdto = ProfileDto(
            "login", "123", "login,", "name", "patr", "123", "123", "123", "123,2"
        )
        val p = Profile("login", "login,", "name", "patr", "123", "123", "123", listOf("123", "2"))
        assertEquals(p, pdto.toProfile())
    }

    @Test
    fun Profileto_toProfileEntity_isCorrect() {

        val p = Profile("login", "login,", "name", "patr", "123", "123", "123", listOf("123", "2"))

        val pEnt = ProfileEntity(
            "login", "123", "login,", "name", "patr", "123", "123", "123,2"
        )
        assertEquals(p.toProfileEntity(), pEnt)
    }

    @Test
    fun enumToBooleanArray_isCorrect() {
        val list = listOf(Interests.JURIS, Interests.IT, Interests.IB)
        val booleanArray =
            booleanArrayOf(true, false, false, true, false, false, true, false, false, false, false)
        Assert.assertArrayEquals(booleanArray, enumToBooleanArray<Interests>(list));
        // assertEquals(booleanArray, enumToBooleanArray<Interests>(list))
    }

    @Test
    fun enumContains_isCorrect() {
        val it_ = Interests.IT
        it_.name
        assertEquals(true, enumContains<Interests>(it_.name))
    }
}