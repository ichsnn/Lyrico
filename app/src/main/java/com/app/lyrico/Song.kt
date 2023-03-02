package com.app.lyrico

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Song(
    var rank: Int,
    var title: String,
    var singer: String,
    var lyrics: String,
    var imgPhoto: Int,
    var releaseDate: String,
    var about: String
) : Parcelable
