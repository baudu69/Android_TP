package com.carlos.tp2.data

import androidx.databinding.InverseMethod
import java.text.SimpleDateFormat
import java.util.*

object LongConverter {
    @JvmStatic
    @InverseMethod("stringToDate")
    fun dateToString(
        value: Date?
    ): String? = value?.let {
        val f = SimpleDateFormat("dd/MM/yy", Locale.getDefault())
        f.format(value)
    }

    @JvmStatic
    fun stringToDate(
        value: String
    ): Date? {
        return try {
            val f = SimpleDateFormat("dd/MM/yy", Locale.getDefault())
            f.parse(value)
        } catch (e: Exception) {
            null
        }
    }
}