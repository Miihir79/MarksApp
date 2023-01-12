package com.mihir.marksapp.model

import android.os.Parcelable
import androidx.annotation.Keep

import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class DataItem(
    val approximateTimeRequired: String?,
    val category: String?,
    val chapters: List<String>,
    val classes: List<String?>,
    val concepts: List<String?>,
    val correctValue: Int?,
    val exams: List<String>,
    val helperText: String?,
    val id: String,
    val imageBaseUrl: String?,
    val level: String?,
    val microConcepts: List<String?>,
    val options: List<Option>,
    val previousYearPapers: List<String>,
    val question: Question,
    val questionLabels: List<String?>,
    val solution: Solution,
    val source: String,
    val subjects: List<String>,
    val topics: List<String?>,
    val type: String,
    val videoSolution: String?
):Parcelable