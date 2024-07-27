package com.benidict.network.remote

import android.content.Context
import com.benidict.domain.model.User
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.IOException
import javax.inject.Inject

class RemoteSource @Inject constructor(
    private val context: Context,
    private val gson: Gson
) {

    suspend fun loadUserProfile(): User {
        return withContext(Dispatchers.IO) {
            val filePath = getFileFromAssets(context)
            val user = gson.fromJson(filePath.readText(), User::class.java)
            user
        }
    }

    suspend fun login(username: String, password: String): User {
        return withContext(Dispatchers.IO) {
            val filePath = getFileFromAssets(context)
            val user = gson.fromJson(filePath.readText(), User::class.java)
            if (username != user.email && password != user.password)
                User(
                    name = "",
                    email = "",
                    password = ""
                )
            else user
        }
    }

    @Throws(IOException::class)
    private fun getFileFromAssets(context: Context): File =
        File(context.cacheDir, "user.json")
            .also {
                if (!it.exists()) {
                    it.outputStream().use { cache ->
                        context.assets.open("user.json").use { inputStream ->
                            inputStream.copyTo(cache)
                        }
                    }
                }
            }
}