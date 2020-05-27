package com.architectcoders.films.common

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

object FileManager {
    inline fun <reified T> readFromJson(filePath: String): T? {
        return try {
            T::class.java.getResourceAsStream(filePath)
                ?.bufferedReader()?.use { it.readLines() }
                ?.let { lines ->
                    var response = ""
                    lines.forEach { line ->
                        response += line
                    }

                    val moshi = Moshi.Builder().build()
                    val jsonAdapter: JsonAdapter<T> = moshi.adapter(T::class.java)

                    jsonAdapter.fromJson(response)
                } ?: run { null }
        } catch (e: Throwable) {
            null
        }
    }
}
