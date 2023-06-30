package com.example.filmsandseries.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.filmsandseries.model.ShowDetails
import com.example.filmsandseries.util.Converter

@Database(entities = [ShowDetails::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class ShowDatabase : RoomDatabase() {

    abstract fun showDao(): ShowDao

}