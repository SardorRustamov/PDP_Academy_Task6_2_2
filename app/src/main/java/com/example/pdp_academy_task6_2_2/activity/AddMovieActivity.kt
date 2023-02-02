package com.example.pdp_academy_task6_2_2.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.pdp_academy_task6_2_2.R
import com.example.pdp_academy_task6_2_2.database.DataBaseClass
import com.example.pdp_academy_task6_2_2.databinding.ActivityAddMovieBinding
import com.example.pdp_academy_task6_2_2.models.MovieData

class AddMovieActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAddMovieBinding

    private lateinit var movieData: MovieData
    lateinit var dataBaseClass: DataBaseClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAddMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataBaseClass = DataBaseClass(this)

        getInfo()

    }

    private fun getInfo() {
        binding.apply {
            saveBtn.setOnClickListener(View.OnClickListener {

                val addMovieName = addMovieName.text.toString()
                val addMovieAuthor = addMovieAuthor.text.toString()
                val addAboutMovie = addAboutMovie.text.toString()
                val addMovieDate =addMovieDate.text.toString()

                if (addAboutMovie.trim().isNotEmpty() && addMovieAuthor.trim().isNotEmpty() && addMovieName.trim().isNotEmpty() && addMovieDate.trim().isNotEmpty()){
                    movieData = MovieData(movieName = addMovieName, movieAuthor = addMovieAuthor,
                        movieDate = addMovieDate, aboutMovie = addAboutMovie)

                    dataBaseClass.addMovie(movieData)

                    Toast.makeText(this@AddMovieActivity, "Successfully", Toast.LENGTH_SHORT).show()

                    finish()
                } else {
                    Toast.makeText(this@AddMovieActivity, "Please enter all information", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}