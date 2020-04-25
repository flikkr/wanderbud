package com.kazymir.tripweaver.`object`

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*

// Using the following API to retrieve information about coutries
// https://restcountries.eu/#api-endpoints-language
// This entity was not used in this prototype, for future implementation
@Entity
data class Location(
    val country: String,
    var countryCode: String            // ISO 3166-1 (2 letter), JSON attribute -> alpha2Code
) {
    @PrimaryKey(autoGenerate = true)
    var lid: Long = 0

    var region: String = ""
    var currencyName: String = ""
    var currencyCode: String = ""       // ISO 4217-2 (3 letter), JSON attribute -> code
    var currencySymbol: Char = '-'
    var language: String = ""
    var languageCode: String = ""       // ISO 639_1 (3 letter), JSON attribute -> iso639_1

    @Ignore
    var isSynced = language != ""
}