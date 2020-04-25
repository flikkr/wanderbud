package com.kazymir.tripweaver.util

import android.content.Context
import java.io.IOException

/**
 * Deal with all assets in app
 */
class AssetManager {
    companion object {
        // For retrieving local JSON file
        fun getJsonDataFromAsset(context: Context, fileName: String): String? {
            val jsonString: String
            try {
                jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
            } catch (ioException: IOException) {
                ioException.printStackTrace()
                return null
            }
            return jsonString
        }
    }
}