package com.example.movieboxxd.movie_detail.data.mapper_impl

import com.example.movieboxxd.common.data.ApiMapper
import com.example.movieboxxd.movie_detail.data.remote.models.MovieDetailDto
import com.example.movieboxxd.movie_detail.domain.models.MovieDetail
import com.example.movieboxxd.movie_detail.domain.models.Review
import com.example.movieboxxd.utils.convertMinutesToHours
import com.example.movieboxxd.utils.formatCast
import com.example.movieboxxd.utils.formatCrew
import com.example.movieboxxd.utils.formatEmptyValue
import com.example.movieboxxd.utils.formatTimeStamp

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
}