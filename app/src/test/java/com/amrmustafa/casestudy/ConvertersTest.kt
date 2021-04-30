package com.amrmustafa.casestudy
import com.amrmustafa.casestudy.utils.inchesConverter
import com.amrmustafa.casestudy.utils.populationToLong
import com.google.common.truth.Truth
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class ConvertersTest {

    @Test
    fun centimeters_to_inchesConverter() {
        val inches = inchesConverter("120")
        Truth.assertThat(inches).isEqualTo("47.244")
    }

    @Test
    fun invalid_population_zero() {
        val population = populationToLong("unknown")
        Truth.assertThat(population).isEqualTo(0L)
    }

    @Test
    fun population_success_converter() {
        val population = populationToLong("100000")
        Truth.assertThat(population).isEqualTo(100000L)
    }
}