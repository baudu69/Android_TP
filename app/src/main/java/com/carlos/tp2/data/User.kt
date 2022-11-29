package com.carlos.tp2.data

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Keep
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.carlos.tp2.BR
import java.util.*

@Keep
data class User(private var _lastname: String? = "", private var _firstname:
String? = "", private var _birthdayDate: Long = 0, private var _gender: String? =
                    "") : Parcelable,
    BaseObservable() {
    var lastname: String?
        @Bindable get() = _lastname
        set(value) {
            _lastname = value
            notifyPropertyChanged(BR.lastname)
        }
    var firstname: String?
        @Bindable get() = _firstname
        set(value) {
            _firstname = value
            notifyPropertyChanged(BR.firstname)
        }
    var birthdayDate: Long
        @Bindable get() = _birthdayDate
        set(value) {
            _birthdayDate = value
            notifyPropertyChanged(BR.birthdayDate)
        }
    var gender: String?
        @Bindable get() = _gender
        set(value) {
            _gender = value
            notifyPropertyChanged(BR.gender)
        }

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readLong(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(lastname)
        parcel.writeString(firstname)
        parcel.writeLong(birthdayDate)
        parcel.writeString(gender)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}