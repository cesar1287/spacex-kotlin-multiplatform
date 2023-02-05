package com.github.cesar1287.scapexkmm.data

import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RocketLaunch(
    @SerialName("flight_number")
    val flightNumber: Int,
    @SerialName("mission_name")
    val missionName: String,
    @SerialName("launch_date_utc")
    val launchDateUTC: String,
    @SerialName("details")
    val details: String?,
    @SerialName("launch_success")
    val launchSuccess: Boolean?,
    @SerialName("links")
    val links: Links
) {
    var launchYear = launchDateUTC.toInstant().toLocalDateTime(TimeZone.UTC).year
}

@Serializable
data class Links(
    @SerialName("mission_patch")
    val patch: String?,
    @SerialName("mission_patch_small")
    val patchSmall: String?,
    @SerialName("article_link")
    val article: String?
)