package io.cro.example

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        legacyButton.setOnClickListener {
            Intent(this, LegacyActivity::class.java).apply {
                startActivity(this)
            }
        }

        retrofitButton.setOnClickListener {
            Intent(this, RetrofitActivity::class.java).apply {
                startActivity(this)
            }
        }
    }
}
