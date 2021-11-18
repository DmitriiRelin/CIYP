package com.ciyp.di

import android.util.Log
import com.ciyp.datalayer.local.LocalDataSource
import com.ciyp.datalayer.remote.ApiService
import com.ciyp.datalayer.remote.RemoteDataSource
import com.ciyp.datalayer.repos.MovieRepositoryImpl
import com.ciyp.domain.MovieRepository
import com.ciyp.domain.entites.MovieDetails
import com.ciyp.datalayer.repos.mapers.Mapper
import com.ciyp.datalayer.repos.mapers.MapperResponsesToMovieDetails
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object CinemaModule {

    @Singleton
    @Provides
    fun provideMapper(): Mapper<MapperResponsesToMovieDetails.Responses, MovieDetails> {
        return MapperResponsesToMovieDetails()
    }

    @Singleton
    @Provides
    fun provideCinemaRepository(
        dataSource: RemoteDataSource,
        localDataSource: LocalDataSource,
        mapper: Mapper<MapperResponsesToMovieDetails.Responses, MovieDetails>,
    ): MovieRepository {
        return MovieRepositoryImpl(dataSource, localDataSource, mapper)
    }


    @Singleton
    @Provides
    fun providesCinemaApi(): ApiService {
        val apiService = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(
                GsonConverterFactory.create(GsonBuilder().setLenient().create())
            )
            .client(createOkHttpClient(ErrorInterceptor()))
            .build()
            .create(ApiService::class.java)
        return apiService
    }

    private fun createOkHttpClient(interceptor: Interceptor): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor)
        return httpClient.build()
    }

    class ErrorInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            val request = chain.request()
            val response = chain.proceed(request)
            if (response.code() >= 400)
                Log.d("errorRequest", "Request: $request \n Response: $response")
            return response
        }
    }


}