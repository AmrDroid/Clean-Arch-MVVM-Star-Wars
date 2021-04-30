package com.amrmustafa.casestudy.utils

import androidx.annotation.StringRes
import com.amrmustafa.casestudy.R
import java.net.UnknownHostException

internal object ExceptionHandler {

    @StringRes
    fun parse(t: Throwable): Int {
        return when (t) {
            is UnknownHostException -> R.string.error_check_internet_connection
            else -> R.string.error_oops_error_occured
        }
    }

}
