package com.example.movieapp.model

import android.os.Parcel
import android.os.Parcelable

data class ModelMovie(
        var id: Int,
        var judul: String?,
        var desc: String?,
        var genre: String?,
        var poster: Int,
        var trailer: Int,
        var rating: Float
): Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readInt(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readInt(),
                parcel.readInt(),
                parcel.readDouble().toFloat()
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeInt(id)
                parcel.writeString(judul)
                parcel.writeString(desc)
                parcel.writeString(genre)
                parcel.writeInt(poster)
                parcel.writeInt(trailer)
                parcel.writeDouble(rating.toDouble())
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<ModelMovie> {
                override fun createFromParcel(parcel: Parcel): ModelMovie {
                        return ModelMovie(parcel)
                }

                override fun newArray(size: Int): Array<ModelMovie?> {
                        return arrayOfNulls(size)
                }
        }
}
