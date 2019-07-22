package com.example.brsample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class MyLocalBroadcastReceiver : BroadcastReceiver() {
    val TAG = "LocalBroadcastReceiver"

    override fun onReceive(context: Context, intent: Intent) {
        val sb = StringBuilder()
        sb.append("Action: ${intent.action}\n")
        sb.append("URI: ${intent.toUri(Intent.URI_INTENT_SCHEME)}\n")
        toString().also { log ->
            Log.d(TAG, log)
        }
    }
}