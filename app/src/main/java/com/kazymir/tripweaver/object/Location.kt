package com.kazymir.tripweaver.`object`

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

// Using the following API to retrieve information about coutries
// https://restcountries.eu/#api-endpoints-language
@Entity
data class Location(
    @ColumnInfo val region: String,
    @ColumnInfo val country: String,
    @ColumnInfo val countryCode: String,        // ISO 3166-1 (2 letter), JSON attribute -> alpha2Code
    @ColumnInfo val currency: String,
    @ColumnInfo val currencyCode: String,       // ISO 4217-2 (3 letter), JSON attribute -> code
    @ColumnInfo val currencySymbol: Char,
    @ColumnInfo val language: String,
    @ColumnInfo val languageCode: String        // ISO 639_1 (3 letter), JSON attribute -> iso639_1
) {

    @PrimaryKey(autoGenerate = true)
    var lid: Long = 0
}