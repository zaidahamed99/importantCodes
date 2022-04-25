package com.example.ex24_simplenotification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {
    private val CHANNEL_ID = "100"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //object binding
        val notificationBtn = findViewById<Button>(R.id.notificationBtn)

        //onClick
        notificationBtn.setOnClickListener {
            createNotificationChannel()
            notifyMessage("Title","Message Body")

        }
    }

    private fun notifyMessage(title: String, message: String) {
        val intent = Intent(this, NotificationReceiverActivity::class.java)
        val intent2 = Intent(this, MainActivity::class.java)
        val intent3 = Intent(this, ThirdActivity::class.java)

        val pendingIntent = PendingIntent.getActivity(this, 1001, intent, 0)
        val pendingIntent2 = PendingIntent.getActivity(this, 1002, intent2, 0)
        val pendingIntent3 = PendingIntent.getActivity(this, 1003, intent3, 0)

        val option1Action = NotificationCompat.Action.Builder(R.drawable.ic_launcher_foreground, "Go to Receiver Page",
        pendingIntent).build()
        val option2Action = NotificationCompat.Action.Builder(R.drawable.ic_launcher_foreground, "Go to the Main Page",
            pendingIntent2).build()
        val option3Action = NotificationCompat.Action.Builder(R.drawable.ic_launcher_foreground, "Go to the Third Page",
            pendingIntent3).build()

        val icon = BitmapFactory.decodeResource(resources, R.drawable.user)
        val mBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setLargeIcon(icon)
            .setSmallIcon(R.drawable.user)
            .setContentTitle(title)
            .setContentText(message)
            .addAction(option1Action)
            .addAction(option2Action)
            .addAction(option3Action)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setWhen(System.currentTimeMillis()+10000)

        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.notify(0, mBuilder.build())
    }

    private fun createNotificationChannel() {
        val name = "EGCI428"
        val description = "EGCI428 Notification Channel"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID, name, importance)

        channel.description = description

        val notificationManager = getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
    }
}