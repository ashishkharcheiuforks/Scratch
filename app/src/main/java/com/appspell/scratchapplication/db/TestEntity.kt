package com.appspell.scratchapplication.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "test_table")
data class TestEntity(
    @PrimaryKey
    val key: String,
    val value: String
)