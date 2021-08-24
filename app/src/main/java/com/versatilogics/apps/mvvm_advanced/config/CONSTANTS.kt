package com.versatilogics.apps.mvvm_advanced.config

enum class MODE {
    LOCAL,
    STAGING,
    PRODUCTION
}

object CONSTANTS {

    val TAG: String = "Advanced-App:"

    // Configuration Controls
    object CONFIG {
        val APP_MODE = MODE.STAGING

        val ENC_KEY = "giiyCCphb5ncc4kMBCJ2z8kvHt3Sbjpi"

        fun isProductionMode(): Boolean = APP_MODE == MODE.PRODUCTION
    }

    // Host
    object SERVER {
        private const val LOCAL_BASE_URL = "https://cb.media/api/"
        private const val STAGING_BASE_URL = "https://cb.media/api/"
        private const val PRODUCTION_BASE_URL = ""
        val BASE_URL by lazy {
            when (CONFIG.APP_MODE) {
                MODE.PRODUCTION -> PRODUCTION_BASE_URL
                MODE.STAGING -> STAGING_BASE_URL
                else -> LOCAL_BASE_URL
            }
        }
    }

    // Network
    object NETWORK {
        const val READ_TIMEOUT: Long = 30
        const val CONNECTION_TIMEOUT: Long = 30

        const val SOMETHING_WENT_WRONG = "Something went wrong.\nReason: %s"
        const val INTERNET_CONNECTIVITY = "Internet connection issue"
        const val NOT_FOUND = "Not Found"
        const val UN_AUTHORIZED = "User Unauthorized"
        const val INTERNAL_SERVER_ERROR = "Server Internal Error"
        const val UNKNOWN_ERROR = "Unknown Error Occurred"
    }

    // Apis
    object API {
        const val GET_VIDEOS = "v1/latestvideos"
    }

    // Keys
    object KEYS {
        const val ID = "id"
    }

    object MESSAGES {
        const val SOMETHING_WENT_WRONG_PUBLIC = "Something went wrong. Please try later."
    }

}