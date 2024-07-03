package com.example.alextimofeev_android_hw14

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PersonProfile (
    @Json(name = "results") val results: List<Person>,
    //@Json(name="info") val info: Info
)

@JsonClass(generateAdapter = true)
data class Person(
    @Json(name = "gender") val gender: String,
    @Json(name = "name") val name: PersonName,
    @Json(name = "location") val location: PersonLocation,
    @Json(name = "email") val email: String,
    @Json(name = "dob") val dob: PersonDOB,
    //@Json(name = "registered") val registered: RegisteredDOB,
    @Json(name = "phone") val phone: String,
    @Json(name = "cell") val cell: String,
    @Json(name = "picture") val picture: PersonPicture,
    //@Json(name = "nat") val nat: String,
)

@JsonClass(generateAdapter = true)
data class PersonName(
    @Json(name = "title") val title: String,
    @Json(name = "first") val first: String,
    @Json(name = "last") val last: String
)

@JsonClass(generateAdapter = true)
data class PersonLocation(
    @Json(name="street") val street: Street,
    @Json(name = "city") val city: String,
    @Json(name = "state") val state: String,
    @Json(name = "country") val country: String,
)

@JsonClass(generateAdapter = true)
data class Street(
    @Json(name="number") val number: String,
    @Json(name="name") val name: String
)

@JsonClass(generateAdapter = true)
data class PersonDOB(
    @Json(name = "date") val date: String,
    @Json(name = "age") val age: Int
)

data class RegisteredDOB(
    @Json(name = "date") val date: String,
    @Json(name = "age") val age: Int
)

@JsonClass(generateAdapter = true)
data class PersonPicture(
    @Json(name="large") val large: String,
    @Json(name="medium") val medium: String,
    @Json(name="thumbnail") val thumbnail: String
)

//@JsonClass(generateAdapter = true)
//data class Info(
//    @Json(name="seed") val seed: String,
//    @Json(name="results") val results: String,
//    @Json(name="page") val page: String,
//    @Json(name="version") val version: String,
//)