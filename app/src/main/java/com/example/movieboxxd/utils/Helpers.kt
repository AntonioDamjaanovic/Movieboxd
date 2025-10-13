package com.example.movieboxxd.utils

import com.example.movieboxxd.movie_detail.data.remote.models.CastDto
import com.example.movieboxxd.movie_detail.data.remote.models.CrewDto
import com.example.movieboxxd.movie_detail.domain.models.Cast
import com.example.movieboxxd.movie_detail.domain.models.Crew
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Locale

fun formatGenre(genreIds: List<Int?>?): List<String> {
    return genreIds?.map { GenreConstants.getGenreNameById(it ?: 0) } ?: emptyList()
}

fun formatTimeStamp(pattern: String = "dd.MM.yy", time: String): String {
    return try {
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
        val outputFormatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
        val zonedDateTime = ZonedDateTime.parse(time, inputFormatter)
        outputFormatter.format(zonedDateTime)
    } catch (e: DateTimeParseException) {
        time
    }
}

fun convertMinutesToHours(minutes: Int): String {
    val hours = minutes / 60
    val remainingMinutes = minutes % 60
    return "${hours}h${remainingMinutes}m"
}

fun formatEmptyValue(value: String?, default: String = ""): String {
    if (value.isNullOrEmpty()) return "Unknown $default"
    return value
}

fun formatCast(castDto: List<CastDto?>?): List<Cast> {
    return castDto?.map {
        val genderRole = if (it?.gender == 2) "Actor" else "Actress"
        Cast(
            id = it?.id ?: 0,
            name = formatEmptyValue(it?.name),
            gender = genderRole,
            character = formatEmptyValue(it?.character),
            profilePath = it?.profilePath
        )
    } ?: emptyList()
}

fun formatCrew(crewDto: List<CrewDto?>?): List<Crew> {
    return crewDto?.map {
        val genderRole = if (it?.gender == 2) "Male" else "Female"
        Crew(
            id = it?.id ?: 0,
            name = formatEmptyValue(it?.name),
            gender = genderRole,
            job = formatEmptyValue(it?.job),
            profilePath = it?.profilePath
        )
    } ?: emptyList()
}