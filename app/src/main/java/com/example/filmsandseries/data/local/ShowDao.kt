package com.example.filmsandseries.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.filmsandseries.model.ShowDetails

@Dao
interface ShowDao {

    @Query("SELECT * FROM show")
    fun getFavoritesShowList(): LiveData<List<ShowDetails>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShow(show: ShowDetails)

    @Delete
    suspend fun deleteFavoriteShow(show: ShowDetails)

}