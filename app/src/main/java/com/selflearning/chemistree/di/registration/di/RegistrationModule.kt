package com.selflearning.chemistree.di.registration.di

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.selflearning.chemistree.feature.f_profile.UnregisterUser
import com.selflearning.chemistree.feature.f_profile.UnregisterUserWithFirebase
import com.selflearning.chemistree.feature.f_registration.RegisterUser
import com.selflearning.chemistree.feature.f_registration.RegisterUserWithFirebase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RegistrationModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(
            auth: FirebaseAuth,
            googleSignInClient: GoogleSignInClient,
            context: Context
    ): RegisterUser {
        return RegisterUserWithFirebase(auth, context, googleSignInClient)
    }

    @Provides
    @Singleton
    fun provideFirebaseSignOut(
            auth: FirebaseAuth
    ) : UnregisterUser {
        return UnregisterUserWithFirebase(auth)
    }
}