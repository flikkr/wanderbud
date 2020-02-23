package com.kazymir.tripweaver.`object`

import java.util.*

// Using the following API to retrieve information about coutries
// https://restcountries.eu/#api-endpoints-language
data class Location(
    val name: String,
    val country: String,
    val countryCode: String,        // ISO 3166-1 (2 letter), JSON attribute -> alpha2Code
    val currency: Currency,
    val currencyCode: String,       // ISO 4217-2 (3 letter), JSON attribute -> code
    val currencySymbol: Char,
    val language: String,
    val languageCode: String        // ISO 639_1 (3 letter), JSON attribute -> iso639_1
) {

}