package com.example.movieboxxd.movie_detail.data.mapper_impl

import com.example.movieboxxd.common.data.ApiMapper
import com.example.movieboxxd.movie_detail.data.remote.models.CastDto
import com.example.movieboxxd.movie_detail.data.remote.models.CrewDto
import com.example.movieboxxd.movie_detail.data.remote.models.MovieDetailDto
import com.example.movieboxxd.movie_detail.domain.models.Cast
import com.example.movieboxxd.movie_detail.domain.models.Crew
import com.example.movieboxxd.movie_detail.domain.models.MovieDetail
import com.example.movieboxxd.movie_detail.domain.models.Review
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Locale

class MovieDetailMapper: ApiMapper<MovieDetail, MovieDetailDto> {
    override fun mapToDomain(apiDto: MovieDetailDto): MovieDetail {
        return MovieDetail(
            backdropPath = formatEmptyValue(apiDto.backdropPath),
            genreIds = apiDto.genres?.map { formatEmptyValue(it?.name) } ?: emptyList(),
            id = apiDto.id ?: 0,
            originalLanguage = formatEmptyValue(apiDto.originalLanguage, "language"),
            originalTitle = formatEmptyValue(apiDto.originalTitle, "title"),
            overview = formatEmptyValue(apiDto.overview, "overview"),
            popularity = apiDto.popularity ?: 0.0,
            posterPath = formatEmptyValue((apiDto.posterPath)),
            releaseDate = formatEmptyValue(apiDto.releaseDate, "date"),
            title = formatEmptyValue(apiDto.title, "title"),
            voteAverage = apiDto.voteAverage ?: 0.0,
            voteCount = apiDto.voteCount ?: 0,
            video = apiDto.video ?: false,
            cast = formatCast(apiDto.credits?.cast),
            crew = formatCrew(apiDto.credits?.crew),
            language = apiDto.spokenLanguages?.map { formatEmptyValue(it?.englishName) }
                ?: emptyList(),
            productionCountry = apiDto.productionCountries?.map { formatEmptyValue(it?.name) }
                ?: emptyList(),
            reviews = apiDto.reviews?.results?.map {
                Review(
                    author = formatEmptyValue(it?.author),
                    content = formatEmptyValue(it?.content),
                    createdAt = formatTimeStamp(time = it?.createdAt ?: "0"),
                    id = formatEmptyValue(it?.id),
                    rating = it?.authorDetails?.rating ?: 0.0
                )
            } ?: emptyList(),
            runTime = convertMinutesToHours(apiDto.runtime ?: 0)
        )
    }

    private fun formatTimeStamp(pattern: String = "dd.MM.yy", time: String): String {
        return try {
            val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
            val outputFormatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
            val zonedDateTime = ZonedDateTime.parse(time, inputFormatter)
            outputFormatter.format(zonedDateTime)
        } catch (e: DateTimeParseException) {
            time
        }
    }

    private fun convertMinutesToHours(minutes: Int): String {
        val hours = minutes / 60
        val remainingMinutes = minutes % 60
        return "${hours}h${remainingMinutes}m"
    }

    private fun formatEmptyValue(value: String?, default: String = ""): String {
        if (value.isNullOrEmpty()) return "Unknown $default"
        return value
    }

    private fun formatCast(castDto: List<CastDto?>?): List<Cast> {
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

    private fun formatCrew(crewDto: List<CrewDto?>?): List<Crew> {
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
}