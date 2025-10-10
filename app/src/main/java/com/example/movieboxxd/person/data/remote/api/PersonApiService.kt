package com.example.movieboxxd.person.data.remote.api

import com.example.movieboxxd.BuildConfig
import com.example.movieboxxd.person.data.remote.models.PersonDto
import com.example.movieboxxd.utils.K
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val PERSON_ID = "person_id"

interface PersonApiService {
    @GET("${K.MOVIE_PERSON_ENDPOINT}/${PERSON_ID}")
    suspend fun fetchPersonDetail(
        @Path(PERSON_ID) personId: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("include_adult") includeAdult: Boolean = false
    ): PersonDto
}