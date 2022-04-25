package com.example.ex25_cloudmessaging

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService: FirebaseMessagingService() {
    val channelId = "notification channel"
    val channelName = "com.example.ex25_cloudmessaging"

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Log.d("CloudMessage", "From: ${remoteMessage.from}")
        if (remoteMessage.data.isEmpty()){
            if (remoteMessage.notification != null){
                Log.d("CloudMessage", "Message data paylod: ${remoteMessage.data}")
                showNotification(remoteMessage.notification!!.title, remoteMessage.notification!!.body)
                Log.d("CloudMessage", "Message data paylod: ${remoteMessage.notification!!.body}")
            }
        }
    }

    private fun showNotification(title: String?, message: String?) {
        val icon = BitmapFactory.decodeResource(resources, R.drawable.user)
        var builder: NotificationCompat.Builder = NotificationCompat.Builder(applicationContext, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setLargeIcon(icon)
            .setOnlyAlertOnce(false)

        builder.setContent(getRemoteView(title, message))

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notificationChannel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
        notificationManager.createNotificationChannel(notificationChannel)
        notificationManager.notify(1, builder.build())
    }

    @SuppressLint("RemoteViewLayout")
    private fun getRemoteView(title: String?, message: String?): RemoteViews? {
        val remoteView = RemoteViews("com.example.ex25_cloudmessaging", R.layout.notification)
        remoteView.setTextViewText(R.id.titleText, title)
        remoteView.setTextViewText(R.id.messageText, message)
        remoteView.setImageViewResource(R.id.imageView, R.drawable.ic_launcher_foreground)

        return remoteView
    }
}