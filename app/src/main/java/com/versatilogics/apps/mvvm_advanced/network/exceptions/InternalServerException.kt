package com.versatilogics.apps.mvvm_advanced.network.exceptions

import com.versatilogics.apps.mvvm_advanced.config.CONSTANTS
import java.io.IOException

class InternalServerException : IOException() {

    override val message: String
        get() = CONSTANTS.NETWORK.INTERNAL_SERVER_ERROR
}