package com.example.pdp_academy_task6_2_2.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.pdp_academy_task6_2_2.R
import com.example.pdp_academy_task6_2_2.database.DataBaseClass
import com.example.pdp_academy_task6_2_2.databinding.ActivityEditMovieBinding
import com.example.pdp_academy_task6_2_2.databinding.ActivityMainBinding
import com.example.pdp_academy_task6_2_2.models.MovieData

class EditMovieActivity : AppCompatActivity() {

    private lateinit var binding : ActivityEditMovieBinding
    private lateinit var list: List<MovieData>
    private lateinit var dataBaseClass: DataBaseClass

    private lateinit var movieData: MovieData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityEditMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataBaseClass= DataBaseClass(this)
        list=dataBaseClass.allMovie()

        val pos = intent.getIntExtra("key", -1)
        val position1 = intent.getIntExtra("key2", -1)

        binding.apply {
            editMovieName.setText(list[position1].movieName)
            editAboutMovie.setText(list[position1].aboutMovie)
            editMovieAuthor.setText(list[position1].movieAuthor)
            editMovieDate.setText(list[position1].movieDate)
        }

        binding.editBtn.setOnClickListener(View.OnClickListener {

            binding.apply {

                val editMovieName = editMovieName.text.toString()
                val editMovieAuthor = editMovieAuthor.text.toString()
                val editAboutMovie = editAboutMovie.text.toString()
                val editMovieDate =editMovieDate.text.toString()

                movieData = MovieData(pos, editMovieName, editMovieAuthor, editAboutMovie, editMovieDate)
                dataBaseClass.updateMovie(movieData)

                finish()
            }
        })
    }
}