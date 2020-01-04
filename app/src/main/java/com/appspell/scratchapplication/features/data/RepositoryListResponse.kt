package com.appspell.scratchapplication.features.data

import com.squareup.moshi.Json

data class RepositoryResponse(
    val id: Long,
    val name: String,
    @Json(name = "full_name")
    val fullName: String
)