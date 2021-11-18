package com.ciyp.di

import android.content.Context
import com.ciyp.ui.detail.DetailsFragment
import com.ciyp.ui.favorites.FavoritesFragment
import com.ciyp.ui.genres.GenresFragment
import com.ciyp.ui.genresDetail.GenresDetailsFragment
import com.ciyp.ui.home.HomeFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CinemaModule::class, FavoritesModule::class])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationContext(applicationContext: Context): Builder
        fun build(): AppComponent
    }

    fun inject(homeFragment: HomeFragment)
    fun inject(favoritesFragment: FavoritesFragment)
    fun inject(detailsFragment: DetailsFragment)
    fun inject(genresFragment: GenresFragment)
    fun inject(genresDetailsFragment: GenresDetailsFragment)
}