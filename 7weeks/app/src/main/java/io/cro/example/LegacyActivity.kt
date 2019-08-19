package io.cro.example

import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.cro.example.adapter.UserAdapter
import kotlinx.android.synthetic.main.activity_legacy.*
import timber.log.Timber
import java.net.HttpURLConnection
import java.net.URL

class LegacyActivity : AppCompatActivity() {
    private val adapter: UserAdapter by lazy { UserAdapter() }
    private val layoutManager: LinearLayoutManager by lazy { LinearLayoutManager(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_legacy)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        with(userList) {
            adapter = this@LegacyActivity.adapter
            layoutManager = this@LegacyActivity.layoutManager
        }

        NetworkTask(adapter).execute("https://api.github.com/users")
    }
}

class NetworkTask(private val adapter: UserAdapter) : AsyncTask<String, String, List<UserProfile>>() {
    override fun doInBackground(vararg params: String?): List<UserProfile>? {
        val url = URL(params[0])
        var users: List<UserProfile>? = null

        with(url.openConnection() as HttpURLConnection) {
            requestMethod = "GET"
            setRequestProperty("Accept", "application/vnd.github.v3+json")

            inputStream.bufferedReader().use {
                val stringBuffer = StringBuffer()
                var inputLine = it.readLine()
                while (!inputLine.isNullOrEmpty()) {
                    stringBuffer.append(inputLine)
                    inputLine = it.readLine()
                }

                users = Gson().fromJson(
                    stringBuffer.toString(),
                    object : TypeToken<List<UserProfile>>() {}.type
                )
            }
        }

        return users
    }

    override fun onPostExecute(result: List<UserProfile>?) {
        super.onPostExecute(result)
        Timber.d("LEGACY::USERS::$result")
        result?.let {
            adapter.updateItem(it)
        }
    }
}
