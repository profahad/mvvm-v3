package com.versatilogics.apps.mvvm_advanced.network.exceptions

import com.versatilogics.apps.mvvm_advanced.config.CONSTANTS
import java.io.IOException

class NoInternetException : IOException() {

    override val message: String
        get() = CONSTANTS.NETWORK.INTERNET_CONNECTIVITY
}