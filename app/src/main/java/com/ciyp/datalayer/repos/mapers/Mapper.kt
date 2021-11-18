package com.ciyp.datalayer.repos.mapers

interface Mapper<I, O> {
    fun map(input: I): O
}