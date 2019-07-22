package com.example.brsample

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class MainActivity : AppCompatActivity() {
    val br: BroadcastReceiver = MyBroadcastReceiver()
    val lbr = MyLocalBroadcastReceiver()
    val pbr = PermissionBroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerBR()
    }

    override fun onDestroy() {
        unRegisterBR()
        super.onDestroy()
    }

    private fun registerBR() {
        val filter = IntentFilter().apply {
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
            addAction("Testing1234")
        }
        registerReceiver(br, filter)

        LocalBroadcastManager.getInstance(this).registerReceiver(lbr, IntentFilter("LocalBroadcastManagerTest"))

        var pfilter = IntentFilter("TEST5678")
        registerReceiver(pbr, pfilter, Manifest.permission.SEND_SMS, null )
    }

    private fun unRegisterBR() {
        unregisterReceiver(br)

        LocalBroadcastManager.getInstance(this).unregisterReceiver(lbr)
    }

    fun onClickBtn1(view: View) {
        Intent().also { intent ->
            intent.setAction("Testing1234")
            intent.putExtra("data", "Notice me senpai!")
            sendOrderedBroadcast(intent, null)
        }
    }

    fun onClickBtn2(view: View) {
        Intent().also { intent ->
            intent.setAction("Testing1234")
            intent.putExtra("data", "Notice me senpai!")
            sendBroadcast(intent)
        }
    }

    fun onClickBtn3(view: View) {
        val intent = Intent("LocalBroadcastManagerTest")
        intent.putExtra("data1", "black")
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
    }

    fun onClickBtn4(view: View) {
        sendBroadcast(Intent("TEST5678"), Manifest.permission.SEND_SMS)
    }
}
