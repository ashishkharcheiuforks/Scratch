package com.appspell.scratchapplication.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.appspell.scratchapplication.db.TestDB
import com.appspell.scratchapplication.db.TestDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule {

    @Provides
    @Singleton
    fun provideDB(application: Application): TestDB =
        Room.databaseBuilder(application, TestDB::class.java, TestDB.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideTestDao(db: TestDB): TestDao = db.getTestDao()

}