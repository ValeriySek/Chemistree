package com.selflearning.chemistree

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.content.ContextCompat
import com.selflearning.chemistree.di.AppComponent
import com.selflearning.chemistree.di.AppModule
import com.selflearning.chemistree.di.DaggerAppComponent
import com.selflearning.chemistree.di.db.DatabaseModule
import com.selflearning.chemistree.utils.daily_notifications.RemindersManager

class ChemistreeApplication : Application() {

    val appComponentFactory: AppComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .databaseModule(DatabaseModule(this))
            .build()

    override fun onCreate() {
        setTheme(R.style.AppTheme_Dark)
        super.onCreate()
        createNotificationsChannels()
        RemindersManager.startReminder(this)
    }

    private fun createNotificationsChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Log.i("TAGG", "createNotificationsChannels")
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channelId = "CHANNEL_ID"
            val channelName = "Chem"
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
        }
    }
}