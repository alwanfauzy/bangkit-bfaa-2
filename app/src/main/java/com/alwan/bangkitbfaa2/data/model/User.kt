package com.alwan.bangkitbfaa2.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: Int? = null,
    val login: String? = null,
    val type: String? = null,
    @SerializedName("avatar_url")
    val avatarUrl: String? = null
) : Parcelable