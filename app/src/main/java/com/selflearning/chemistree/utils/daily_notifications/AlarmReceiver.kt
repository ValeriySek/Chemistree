package com.selflearning.chemistree.utils.daily_notifications

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.selflearning.chemistree.R
import com.selflearning.chemistree.activities.MainActivity

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.i("TAGG", "onReceive")
//        val notificationManager = ContextCompat.getSystemService(
//            context,
//            NotificationManager::class.java
//        ) as NotificationManager
//
//        notificationManager.
        sendReminderNotification(
            applicationContext = context,
            channelId = "CHANNEL_ID"
        )

        RemindersManager.startReminder(context.applicationContext)
    }
}

fun NotificationManager.sendReminderNotification(
    applicationContext: Context,
    channelId: String,
) {
    val contentIntent = Intent(applicationContext, MainActivity::class.java)
    val pendingIntent = PendingIntent.getActivity(
        applicationContext,
        1,
        contentIntent,
        PendingIntent.FLAG_UPDATE_CURRENT
    )
    val builder = NotificationCompat.Builder(applicationContext, channelId)
        .setContentTitle("title_notification_reminder")
        .setContentText("description_notification_reminder")
        .setSmallIcon(R.drawable.ic_login)
        .setStyle(
            NotificationCompat.BigTextStyle()
                .bigText("bigText")
        )
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)

//    val notificationManagerCompat = NotificationManagerCompat.from(applicationContext)
//    notificationManagerCompat.
    notify(NOTIFICATION_ID, builder.build())
}

fun sendReminderNotification(
    applicationContext: Context,
    channelId: String,
) {
    val contentIntent = Intent(applicationContext, MainActivity::class.java)
    val pendingIntent = PendingIntent.getActivity(
        applicationContext,
        1,
        contentIntent,
        PendingIntent.FLAG_UPDATE_CURRENT
    )
    val builder = NotificationCompat.Builder(applicationContext, channelId)
        .setContentTitle("title_notification_reminder")
        .setContentText("description_notification_reminder")
        .setSmallIcon(R.drawable.ic_login)
        .setStyle(
            NotificationCompat.BigTextStyle()
                .bigText("bigText")
        )
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)

    NotificationManagerCompat
        .from(applicationContext)
        .notify(NOTIFICATION_ID, builder.build())
}

const val NOTIFICATION_ID = 1