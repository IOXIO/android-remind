package com.tistory.ykyahwa.app2

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView



class MainActivity : AppCompatActivity() {

    var authority = "com.ykyahwa.cptest"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val c = contentResolver.query(Uri.parse("content://$authority/getdataall"), null, null, null, null) ?: return
        Log.e("test", "aaabbb " + c.count)

        val aa = StringBuilder()
        while (c.moveToNext()) {
            val str = c.getLong(0).toString() + " , " + c.getString(1) + " , " + c.getString(2) + " , " + c.getInt(3)
            println(str)
            aa.append(str + "\n")
        }
        c.close()

        val text = findViewById(R.id.text) as TextView
        text.text = aa
    }
}
