package com.appspell.scratchapplication.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable
import io.reactivex.Maybe

@Dao
interface TestDao {

    @Query("SELECT * FROM test_table")
    fun getAll(): Flowable<List<TestEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(entity: TestEntity): Maybe<Long>

    @Query("DELETE FROM test_table")
    fun clearAll()
}