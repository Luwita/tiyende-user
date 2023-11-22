package com.shuttleclone.userapp.fcmService

import android.media.RingtoneManager
import com.google.firebase.messaging.FirebaseMessagingService
import com.shuttleclone.userapp.utils.mylog
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.RemoteMessage
import com.shuttleclone.userapp.utils.Constants
import rigo.rigotaxi.rigouserapp.UtilMethod.showAlertNotification
import rigo.rigotaxi.rigouserapp.UtilMethod.showPopInfoNotification
import java.lang.Exception

class MyFirebaseMessagingService : FirebaseMessagingService() {

    private var title = ""
    private var body = ""
    private var infoPopUp: String? = ""
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        try {
            mylog(TAG, " data=" + remoteMessage.data)
            mylog(TAG, " title=" + remoteMessage.data["title"])
            mylog(TAG, " message=" + remoteMessage.data["message"])
            mylog(TAG, " step=" + remoteMessage.data["step"])
            mylog(TAG, " booking=" + remoteMessage.data["booking"])
            mylog(TAG, " Notification=" + remoteMessage.notification)
        } catch (e: Exception) {
            mylog(TAG, " Error=" + e.localizedMessage)
        }
        if (remoteMessage != null) {
            showNotification(remoteMessage)
        }
    }
    
    private fun showBookingNotification(title: String, message: String, notificationId: Int) {
        try {
            showPopInfoNotification(
                CHANNEL_ID,
                title,
                message,
                "",
                notificationId
            )
            val notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val r = RingtoneManager.getRingtone(applicationContext, notification)
            r.play()
        } catch (e: Exception) {
            mylog(TAG, "showBookingNotification: Error=${e.localizedMessage}")
        }
    }

    private fun showNotification(remoteMessage: RemoteMessage) {
        try {
            if (remoteMessage.notification != null) {
                title = remoteMessage.notification!!.title.toString()
                body = remoteMessage.notification!!.body.toString()
            } else {
                title = remoteMessage.data["title"].toString()
                body = remoteMessage.data["message"].toString()
                infoPopUp = remoteMessage.data["info_popup"]
                mylog(TAG, "showNotification: infoPopUp=$infoPopUp")
            }
            if (infoPopUp != null) this.showPopInfoNotification(
                CHANNEL_ID,
                title,
                body,
                infoPopUp!!,
                2121
            ) else this.showAlertNotification(
                CHANNEL_ID, title, body
            )
            try {
                val notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                val r = RingtoneManager.getRingtone(this, notification)
                r.play()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } catch (e: Exception) {
            mylog(TAG, "showNotification: Err0r=" + e.localizedMessage)
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        mylog("NEW_TOKEN = = == = = =", token)
        FirebaseMessaging.getInstance().subscribeToTopic(SUBSCRIBE_TO)
    }

    companion object {
        private val CHANNEL_ID = Constants.CHANNEL_ID
        private const val TAG = "MainFirebaseMessaging"
        private const val SUBSCRIBE_TO = "User"
    }
}