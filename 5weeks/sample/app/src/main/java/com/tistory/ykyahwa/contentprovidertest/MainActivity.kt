package com.tistory.ykyahwa.contentprovidertest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView




class MainActivity : AppCompatActivity() {

    lateinit var mDatabase: DBSample

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mDatabase = DBSample(baseContext)

        // 데이터 입력
        mDatabase.setDelete()
        val mList: ArrayList<ItemRow> = ArrayList()
        mList.add(ItemRow("식사", "미역국", 5000))
        mList.add(ItemRow("간식", "우유", 4000))
        mList.add(ItemRow("간식", "바나나", 3000))
        mList.add(ItemRow("식사", "오이", 2000))
        mList.add(ItemRow("식사", "당근", 1000))
        for (item in mList) {
            mDatabase.setItem(item.contents, item.name, item.num)
        }

        val bt_renew = findViewById<Button>(R.id.bt_renew)
        bt_renew.setOnClickListener { loadData() }
    }

    private fun loadData() {
        val sb = StringBuilder()
        val row = mDatabase.getItem()
        sb.append("Total count : " + row.size + "\n\n")
        for ((contents, name, num) in row) {
            sb.append("$contents , $name , $num\n")
        }
        val tv_text = findViewById<TextView>(R.id.tv_text)
        tv_text.text = sb.toString()
    }

    override fun onDestroy() {
        super.onDestroy()
        mDatabase.close()
    }


}
