package com.selflearning.chemistree.di

import com.selflearning.chemistree.activities.LaunchActivity
import com.selflearning.chemistree.activities.MainActivity
import com.selflearning.chemistree.di.db.DatabaseModule
import com.selflearning.chemistree.di.firebase.di.FirebaseModule
import com.selflearning.chemistree.di.registration.di.RegistrationModule
import com.selflearning.chemistree.di.view_model.di.ViewModelModule
import com.selflearning.chemistree.feature.f_home.HomeFragment
import com.selflearning.chemistree.feature.f_profile.ProfileFragment
import com.selflearning.chemistree.feature.f_registration.RegisterActivity
import com.selflearning.chemistree.feature.f_registration.RegisterFragment
import com.selflearning.chemistree.games.PreGameActivity
import com.selflearning.chemistree.games.game4.GameFragment4
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    RegistrationModule::class,
    FirebaseModule::class,
    ViewModelModule::class,
    DatabaseModule::class
])
interface AppComponent {

    fun inject(activity: RegisterActivity)
    fun inject(activity: LaunchActivity)
    fun inject(activity: MainActivity)
    fun inject(activity: PreGameActivity)

    fun inject(fragment: RegisterFragment)
    fun inject(fragment: ProfileFragment)
    fun inject(fragment: HomeFragment)
    fun inject(fragment: GameFragment4)
}