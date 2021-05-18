package com.selflearning.chemistree

import android.app.Application
import com.selflearning.chemistree.di.AppComponent
import com.selflearning.chemistree.di.AppModule
import com.selflearning.chemistree.di.DaggerAppComponent
import com.selflearning.chemistree.di.db.DatabaseModule

class ChemistreeApplication : Application(){

    val appComponentFactory: AppComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .databaseModule(DatabaseModule(this))
            .build()
}