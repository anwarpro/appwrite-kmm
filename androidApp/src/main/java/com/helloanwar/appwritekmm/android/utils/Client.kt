package com.helloanwar.appwritekmm.android.utils

import android.content.Context
import io.appwrite.Client

object Client {
    lateinit var client: Client

    fun create(context: Context) {
        client = Client(context)
            .setEndpoint("https://cloud.appwrite.io/v1")
            .setProject("64c5606da0a3626bc6da")

        /* Useful when testing locally */
//        client = Client(context)
//            .setEndpoint("https://192.168.1.35/v1")
//            .setProject("60bdbc911784e")
//            .setSelfSigned(true)
    }
}