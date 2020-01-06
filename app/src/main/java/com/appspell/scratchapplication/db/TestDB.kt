package com.appspell.scratchapplication.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 1,
    entities = [DbEntity::class])
abstract class TestDB : RoomDatabase() {

    companion object {
        const val DB_NAME = "test_db"
    }

    abstract fun getTestDao() : TestDao
}