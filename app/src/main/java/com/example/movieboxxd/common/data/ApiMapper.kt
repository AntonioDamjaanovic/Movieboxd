package com.example.movieboxxd.common.data

interface ApiMapper<Domain, Entity> {
    fun mapToDomain(apiDto: Entity): Domain
}