package com.selflearning.chemistree.di

import androidx.fragment.app.FragmentActivity
import com.selflearning.chemistree.activities.LaunchActivity
import com.selflearning.chemistree.activities.MainActivity
import com.selflearning.chemistree.di.firebase.di.FirebaseModule
import com.selflearning.chemistree.di.registration.di.RegistrationModule
import com.selflearning.chemistree.f_registration.RegisterActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    RegistrationModule::class,
    FirebaseModule::class
])
interface AppComponent {

    fun inject(activity: RegisterActivity)
    fun inject(activity: LaunchActivity)
    fun inject(activity: MainActivity)
}