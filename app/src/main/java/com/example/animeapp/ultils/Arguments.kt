package com.example.animeapp.ultils

import android.os.Parcel
import android.os.Parcelable

data class Arguments(
    val id: Int,
    val type: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Arguments> {
        override fun createFromParcel(parcel: Parcel): Arguments {
            return Arguments(parcel)
        }

        override fun newArray(size: Int): Array<Arguments?> {
            return arrayOfNulls(size)
        }
    }
}
