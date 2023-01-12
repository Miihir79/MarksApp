package com.mihir.marksapp.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Question(
    val image: String?,
    val text: String
):Parcelable