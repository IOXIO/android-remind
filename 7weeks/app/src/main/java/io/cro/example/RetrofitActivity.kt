package io.cro.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import io.cro.example.adapter.UserAdapter
import kotlinx.android.synthetic.main.activity_legacy.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import timber.log.Timber
import java.io.IOException

class RetrofitActivity : AppCompatActivity() {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpHeaderInterceptor())
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val adapter: UserAdapter by lazy { UserAdapter() }
    private val layoutManager: LinearLayoutManager by lazy { LinearLayoutManager(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        with(userList) {
            adapter = this@RetrofitActivity.adapter
            layoutManager = this@RetrofitActivity.layoutManager
        }

        getUsers()
    }

    private fun getUsers() {
        retrofit.create(GitHubApi::class.java)
            .getUsers().enqueue(object : Callback<List<UserProfile>> {
                override fun onResponse(
                    call: Call<List<UserProfile>>,
                    response: retrofit2.Response<List<UserProfile>>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            adapter.updateItem(it)
                        }
                    }
                }

                override fun onFailure(call: Call<List<UserProfile>>, t: Throwable) {
                    Timber.e("RETROFIT::onFailure::${t.message}")
                }
            })
    }
}

class HttpHeaderInterceptor: Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBuilder = request.newBuilder().apply {
            addHeader("Accept", "application/vnd.github.v3+json")
        }

        return chain.proceed(requestBuilder.build())
    }

    companion object {
        private enum class Headers(val key: String, val value: String) {
            ACCEPT("Accept", "application/vnd.github.v3+json");
        }
    }
}

interface GitHubApi {
    @GET("/users")
    fun getUsers(): Call<List<UserProfile>>
}
