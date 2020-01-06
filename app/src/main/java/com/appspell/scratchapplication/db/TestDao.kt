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
    fun getAll(): Flowable<List<DbEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(entity: DbEntity): Maybe<Long>
}