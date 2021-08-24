package com.versatilogics.apps.mvvm_advanced.network.exceptions

import com.versatilogics.apps.mvvm_advanced.config.CONSTANTS
import java.lang.Exception

class UnKnownException : Exception() {

    override val message: String
        get() = CONSTANTS.NETWORK.UNKNOWN_ERROR
}