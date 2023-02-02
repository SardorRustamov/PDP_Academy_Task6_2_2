package com.example.pdp_academy_task6_2_2.database

import com.example.pdp_academy_task6_2_2.models.MovieData

interface DataBaseService {
    fun addMovie(movieData: MovieData)

    fun updateMovie(movieData: MovieData) :Int

    fun deleteMovie(movieData: MovieData)

    fun allMovie(): List<MovieData>

}