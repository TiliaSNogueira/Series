package com.example.filmsandseries.di

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.filmsandseries.R
import com.example.filmsandseries.data.RepositoryImpl
import com.example.filmsandseries.data.RepositoryInterface
import com.example.filmsandseries.data.local.ShowDao
import com.example.filmsandseries.data.local.ShowDatabase
import com.example.filmsandseries.data.remote.FilmsAndSeriesAPI
import com.example.filmsandseries.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun injectRetrofitAPI(): FilmsAndSeriesAPI {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL).build().create(FilmsAndSeriesAPI::class.java)
    }

    @Singleton
    @Provides
    fun injectRoomDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, ShowDatabase::class.java, "show-database"
    ).build()

    @Singleton
    @Provides
    fun injectDao(database: ShowDatabase) = database.showDao()

    @Singleton
    @Provides
    fun injectRepository(api: FilmsAndSeriesAPI, dao: ShowDao) = RepositoryImpl(api, dao) as RepositoryInterface

    @Singleton
    @Provides
    fun injectGlide(@ApplicationContext context: Context) =
        Glide.with(context).setDefaultRequestOptions(
            RequestOptions().placeholder(R.drawable.baseline_tv_24)
                .error(R.drawable.baseline_tv_24)
        )

}