package com.ciyp.di

import android.content.Context
import androidx.room.Room
import com.ciyp.datalayer.local.FavoriteDB
import com.ciyp.datalayer.local.FavoriteDao
import com.ciyp.datalayer.local.LocalDataSource
import com.ciyp.datalayer.local.dto.FavoriteMovieWithCast
import com.ciyp.datalayer.repos.FavoritesRepositoryImpl
import com.ciyp.domain.FavoritesRepository
import com.ciyp.domain.entites.MovieDetails
import com.ciyp.datalayer.repos.mapers.ListMapper
import com.ciyp.datalayer.repos.mapers.ListMapperFavoriteMovieWithCastToMovieDetails
import com.ciyp.datalayer.repos.mapers.MapperFavoriteMovieWithCastToMovieDetails
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object FavoritesModule {

    @Singleton
    @Provides
    fun provideMapper(): ListMapper<FavoriteMovieWithCast, MovieDetails> {
        val mapper = MapperFavoriteMovieWithCastToMovieDetails()
        return ListMapperFavoriteMovieWithCastToMovieDetails(mapper)
    }

    @Singleton
    @Provides
    fun provideFavoriteRepository(
        localDataSource: LocalDataSource,
        mapper: ListMapper<FavoriteMovieWithCast, MovieDetails>,
    ): FavoritesRepository {
        return FavoritesRepositoryImpl(localDataSource, mapper)
    }

    private const val DB_NAME = "Favorite.db"

    @Singleton
    @Provides
    fun provideFavoriteDao(context: Context): FavoriteDao {
        val db = Room.databaseBuilder(
            context,
            FavoriteDB::class.java,
            DB_NAME
        )
            .build()
        return db.favoriteDao()
    }

}