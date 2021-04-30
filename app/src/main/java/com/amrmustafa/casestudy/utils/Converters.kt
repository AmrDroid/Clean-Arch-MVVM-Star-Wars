package com.amrmustafa.casestudy.utils

import java.math.BigDecimal
import java.math.RoundingMode

internal fun inchesConverter(centimeters: String): String =
    (BigDecimal(centimeters.toDouble() * 0.3937).setScale(3, RoundingMode.HALF_DOWN)).toString()

internal fun populationToLong(population: String): Long {
    return if (population.contains("unknown", ignoreCase = true)) 0L else population.toLong()
}