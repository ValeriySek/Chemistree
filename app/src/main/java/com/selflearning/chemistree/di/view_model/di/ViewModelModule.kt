package com.selflearning.chemistree.di.view_model.di

import androidx.lifecycle.ViewModel
import com.selflearning.chemistree.chemistry.elements.AppDatabase
import com.selflearning.chemistree.feature.f_home.HomeViewModel
import com.selflearning.chemistree.feature.f_profile.ProfileViewModel
import com.selflearning.chemistree.feature.f_profile.UnregisterUser
import com.selflearning.chemistree.feature.f_registration.RegisterUser
import com.selflearning.chemistree.feature.f_registration.RegisterViewModel
import com.selflearning.chemistree.games.game.Game0ViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider

@Module
class ViewModelModule {

    @Provides
    fun bindViewModelFactory(
            providerMap: MutableMap<Class<out ViewModel>, Provider<ViewModel>>
    ): ViewModelFactory {
        return ViewModelFactory(providerMap)
    }

    @Provides
    @IntoMap
    @ViewModelKey(RegisterViewModel::class)
    fun provideRegisterViewModel(registerUser: RegisterUser): ViewModel {
        return RegisterViewModel(registerUser)
    }

    @Provides
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    fun provideProfileViewModel(unregisterUser: UnregisterUser): ViewModel {
        return ProfileViewModel(unregisterUser)
    }

    @Provides
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun provideHomeViewModel(appDatabase: AppDatabase): ViewModel {
        return HomeViewModel(appDatabase)
    }

    @Provides
    @IntoMap
    @ViewModelKey(Game0ViewModel::class)
    fun provideGame0ViewModel(): ViewModel {
        return Game0ViewModel()
    }
}