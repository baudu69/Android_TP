package com.carlos.tp2.data

import androidx.annotation.Keep
import java.util.*

@Keep
data class User(
    var lastname: String? = null,
    var firstname: String? = null,
    var birthdayDate: Date? = null,
    var gender: String? = null
) : java.io.Serializable