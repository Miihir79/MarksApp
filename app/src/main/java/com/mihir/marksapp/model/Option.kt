package com.mihir.marksapp.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Option(
    val id: String,
    val image: String?,
    val isCorrect: Boolean,
    val text: String
):Parcelable