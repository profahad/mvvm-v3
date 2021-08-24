package com.versatilogics.apps.mvvm_advanced.network.exceptions

import com.versatilogics.apps.mvvm_advanced.config.CONSTANTS
import java.io.IOException

class UnAuthorizedException : IOException() {

    override val message: String
        get() = CONSTANTS.NETWORK.UN_AUTHORIZED
}