package de.barut.mybeworbenapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import de.barut.mybeworbenapp.room.RoomDB
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomDBModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context : Context) : RoomDB
    = Room.databaseBuilder(context,RoomDB::class.java,"Bewerben").build()


    @Singleton
    @Provides
    fun provideDao(database : RoomDB) = database.dao()


}