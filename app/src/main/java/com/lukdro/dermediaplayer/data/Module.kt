package com.lukdro.dermediaplayer.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class Module {

    @Provides
    @Singleton
    fun provideMemeRepo(): IMusicRepo{
        return MusicRepo()
    }
}