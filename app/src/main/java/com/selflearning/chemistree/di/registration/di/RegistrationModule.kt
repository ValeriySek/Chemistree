package com.selflearning.chemistree.di.registration.di

import com.selflearning.chemistree.f_registration.RegisterUser
import com.selflearning.chemistree.f_registration.RegisterUserWithFirebase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RegistrationModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): RegisterUser {
        return RegisterUserWithFirebase()
    }
}