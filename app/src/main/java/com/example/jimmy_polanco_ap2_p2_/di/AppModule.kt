package com.example.jimmy_polanco_ap2_p2_.di

import com.example.jimmy_polanco_ap2_p2_.data.remote.Api.ApiJugador
import com.example.jimmy_polanco_ap2_p2_.data.remote.remotedatasource.JugadorRemoteDataSource
import com.example.jimmy_polanco_ap2_p2_.data.repository.JugadorRepositoryImpl
import com.example.jimmy_polanco_ap2_p2_.domain.repository.JugadorRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://gestionhuacalesapi.azurewebsites.net/") // tu API de jugadores
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Singleton
    @Provides
    fun provideJugadorApi(retrofit: Retrofit): ApiJugador {
        return retrofit.create(ApiJugador::class.java)
    }

    @Singleton
    @Provides
    fun provideJugadorRemoteDataSource(api: ApiJugador): JugadorRemoteDataSource {
        return JugadorRemoteDataSource(api)
    }

    @Singleton
    @Provides
    fun provideJugadorRepository(remoteDataSource: JugadorRemoteDataSource): JugadorRepository {
        return JugadorRepositoryImpl(remoteDataSource)
    }
}