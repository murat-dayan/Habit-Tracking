package com.example.habittracking.di

import android.content.Context
import androidx.room.Room
import com.example.habittracking.data.repo.HabitsDaoRepository
import com.example.habittracking.data.repo.HabitsRepository
import com.example.habittracking.room.HabitsDao
import com.example.habittracking.room.RmDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideHabitsRepo(): HabitsRepository {
        return HabitsRepository()
    }

    @Provides
    @Singleton
    fun providesHabitsDaoRepo(hDao:HabitsDao) : HabitsDaoRepository{
        return HabitsDaoRepository(hDao)
    }

    @Provides
    @Singleton
    fun providesHabitsDao(@ApplicationContext context: Context): HabitsDao {
        val db = Room.databaseBuilder(context, RmDatabase::class.java, "habitTracking.sqlite")
            .createFromAsset("habitTracking.sqlite").build()

        return db.getHabitsDao()
    }


}